package com.devok.giggz.service.service;

import com.devok.giggz.service.dto.FeedContentDTO;

import java.util.List;

public interface FeedContentService {
    void saveAll(List<FeedContentDTO> feedContentDTOS);
    boolean existsByUrl(String url);
}
