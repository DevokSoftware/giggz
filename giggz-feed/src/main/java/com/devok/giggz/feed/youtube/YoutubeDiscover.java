package com.devok.giggz.feed.youtube;

import com.devok.giggz.service.dto.FeedContentDTO;
import com.devok.giggz.service.enums.SourceType;
import com.devok.giggz.service.model.Comedian;
import com.devok.giggz.service.model.feed.FeedChannel;
import com.devok.giggz.service.repository.ComedianRepository;
import com.devok.giggz.service.service.FeedChannelService;
import com.devok.giggz.service.service.FeedContentService;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeRequestInitializer;
import com.google.api.services.youtube.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class YoutubeDiscover {
    private static final Logger LOG = LoggerFactory.getLogger(YoutubeDiscover.class);
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private final FeedChannelService feedChannelService;
    private final FeedContentService feedContentService;

    @Value("${youtube.api.key}")
    private String apiKey;

    public YoutubeDiscover(FeedChannelService feedChannelService,
                           FeedContentService feedContentService) {
        this.feedChannelService = feedChannelService;
        this.feedContentService = feedContentService;
    }

    public void syncLatestVideos() {
        try {
            YouTube youtubeService = new YouTube.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JSON_FACTORY,
                    request -> {
                    })
                    .setApplicationName("youtube-api-demo")
                    .setYouTubeRequestInitializer(new YouTubeRequestInitializer(apiKey))
                    .build();
            List<FeedChannel> channels = feedChannelService.getAllChannelsBySource(SourceType.YOUTUBE);

            for (FeedChannel channel : channels) {
                String uploadsPlaylistId = getUploadsPlaylistId(youtubeService, channel.getChannelId());
                if (uploadsPlaylistId == null) {
                    LOG.info("No uploads playlist for channel: {}", channel.getChannelId());
                    continue;
                }

                List<FeedContentDTO> newVideos = getRecentVideos(youtubeService, uploadsPlaylistId, channel);
                feedContentService.saveAll(newVideos);
                feedChannelService.updateLastSync(channel, OffsetDateTime.now());

            }
        } catch (GeneralSecurityException | IOException ex) {
            LOG.error("YouTube sync failed: {}", ex.getMessage(), ex);
        }
    }


    private static String getUploadsPlaylistId(YouTube youtube, String channelId) throws IOException {
        YouTube.Channels.List channelRequest = youtube.channels()
                .list("contentDetails")
                .setId(channelId);

        ChannelListResponse channelResponse = channelRequest.execute();
        List<Channel> channels = channelResponse.getItems();

        if (channels != null && !channels.isEmpty()) {
            return channels.get(0)
                    .getContentDetails()
                    .getRelatedPlaylists()
                    .getUploads();
        }
        return null;
    }

    private List<FeedContentDTO> getRecentVideos(YouTube youtube, String playlistId, FeedChannel channel) throws IOException {
        YouTube.PlaylistItems.List playlistRequest = youtube.playlistItems()
                .list("snippet")
                .setPlaylistId(playlistId)
                .setMaxResults(3L); //since its updated every hour, theres no need to fetch many results
        PlaylistItemListResponse playlistResponse = playlistRequest.execute();

        return mapFeedContentResponse(channel.getChannelId(), playlistId, playlistResponse);
    }

    private List<FeedContentDTO> mapFeedContentResponse(String channelId, String playlistId, PlaylistItemListResponse playlistResponse) {
        return playlistResponse.getItems().stream()
                .map(item -> {
                    FeedContentDTO dto = new FeedContentDTO();
                    dto.setSource(SourceType.YOUTUBE);
                    dto.setChannelId(channelId);
                    dto.setUrl("https://www.youtube.com/watch?v=" + item.getSnippet().getResourceId().getVideoId());
                    dto.setThumbnailUrl(item.getSnippet().getThumbnails().getDefault().getUrl());
                    dto.setTitle(item.getSnippet().getTitle());
                    DateTime publishedAt = item.getSnippet().getPublishedAt();
                    if (publishedAt != null) {
                        Instant instant = Instant.ofEpochMilli(publishedAt.getValue());
                        OffsetDateTime offsetDateTime = instant.atZone(ZoneId.systemDefault()).toOffsetDateTime();
                        dto.setAddedDate(offsetDateTime);
                    }
                    dto.setPlaylistId(playlistId);
                    return dto;
                })
                .toList();
    }
}


//    private static String getChannelId(YouTube youtube, String query) throws IOException {
//        YouTube.Search.List search = youtube.search()
//                .list("snippet");
//        search.setQ(query);
//        search.setType("channel");
//        search.setMaxResults(1L);
//
//        SearchListResponse response = search.execute();
//        List<SearchResult> items = response.getItems();
//
//        if (items != null && !items.isEmpty()) {
//            return items.get(0).getSnippet().getChannelId();
//        }
//        return null;
//    }


//            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
//            List<Map<String, Object>> items = (List<Map<String, Object>>) response.getBody().get("items");
//
//            for (Map<String, Object> item : items) {
//                Map<String, Object> id = (Map<String, Object>) item.get("id");
//                if (!"video".equals(id.get("kind"))) continue;
//
//                String videoId = (String) id.get("videoId");
//                String videoUrl = "https://www.youtube.com/watch?v=" + videoId;
//
//                if (contentRepo.existsByUrl(videoUrl)) continue; // skip duplicates
//
//                Map<String, Object> snippet = (Map<String, Object>) item.get("snippet");
//                String title = (String) snippet.get("title");
//                String dateStr = (String) snippet.get("publishedAt");
//                LocalDateTime releaseDate = ZonedDateTime.parse(dateStr).toLocalDateTime();
//
//                ComedianContent content = new ComedianContent();
//                content.setTitle(title);
//                content.setUrl(videoUrl);
//                content.setReleaseDate(releaseDate);
//                content.setType("YOUTUBE");
//                content.setComedian(comedian);
//                content.setPublished(true);
//
//                contentRepo.save(content);
//            }