package com.devok.giggz.service.service;


import java.util.List;

import com.devok.giggz.service.dto.NewsContentDTO;


public interface ContentService {
    List<NewsContentDTO> getLastYoutubeContentUpdates();
    List<NewsContentDTO> getLastPodcastsContentUpdates();
}
