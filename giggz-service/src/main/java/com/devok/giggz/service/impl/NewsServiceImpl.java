package com.devok.giggz.service.impl;

import org.springframework.stereotype.Service;

import com.devok.giggz.service.ContentService;
import com.devok.giggz.service.NewsService;
import com.devok.giggz.service.StandupService;
import com.devok.giggz.service.dto.NewsDTO;

@Service
public class NewsServiceImpl implements NewsService {
    private final StandupService standupService;
    private final ContentService contentService;

    public NewsServiceImpl(StandupService standupService, ContentService contentService) {
        this.standupService = standupService;
        this.contentService = contentService;
    }

    @Override
    public NewsDTO getNews() {
        return NewsDTO.builder()
                .standups(standupService.getLastStandupUpdates())
                .youtube(contentService.getLastYoutubeContentUpdates())
                .podcasts(contentService.getLastPodcastsContentUpdates())
                .build();
    }
}
