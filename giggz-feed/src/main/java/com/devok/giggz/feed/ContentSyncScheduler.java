package com.devok.giggz.feed;

import com.devok.giggz.feed.spotify.SpotifyDiscover;
import com.devok.giggz.feed.youtube.YoutubeDiscover;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ContentSyncScheduler {

    private final YoutubeDiscover youtubeDiscover;
//    private final SpotifyDiscover spotifyDiscover;

    public ContentSyncScheduler(YoutubeDiscover youtubeDiscover) {
        System.out.println("ContentSyncScheduler bean created");
        this.youtubeDiscover = youtubeDiscover;
//        this.spotifyDiscover = spotifyDiscover;
    }

    @Scheduled(cron = "0 * * * * *")
    public void syncNewContent() {
        youtubeDiscover.syncLatestVideos();
//        spotifyDiscover.syncLatestPodcasts();
    }
}