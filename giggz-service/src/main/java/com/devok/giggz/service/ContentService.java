package com.devok.giggz.service;


import java.util.List;

import com.devok.giggz.service.dto.ComedianContentDTO;
import com.devok.giggz.service.dto.NewsContentDTO;


public interface ContentService {
    List<NewsContentDTO> getLastYoutubeContentUpdates();
    List<NewsContentDTO> getLastPodcastsContentUpdates();
}
