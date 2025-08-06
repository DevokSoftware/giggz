package com.devok.giggz.service.service;

import com.devok.giggz.service.dto.FeedContentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FeedContentService {
    Page<FeedContentDTO> findAll(Pageable pageable);
    void saveAll(List<FeedContentDTO> feedContentDTOS);
    boolean existsByUrl(String url);
}
