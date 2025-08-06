package com.devok.giggz.service.service.impl;

import com.devok.giggz.service.dto.FeedContentDTO;
import com.devok.giggz.service.mapper.feed.FeedContentMapper;
import com.devok.giggz.service.repository.feed.FeedContentRepository;
import com.devok.giggz.service.service.FeedContentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedContentServiceImpl implements FeedContentService {
    private final FeedContentRepository feedContentRepository;
    private final FeedContentMapper feedContentMapper;

    public FeedContentServiceImpl(FeedContentRepository feedContentRepository,
                                  FeedContentMapper feedContentMapper) {
        this.feedContentRepository = feedContentRepository;
        this.feedContentMapper = feedContentMapper;
    }

    @Override
    public void saveAll(List<FeedContentDTO> feedContentDTOS) {
        feedContentRepository.saveAll(feedContentMapper.toFeedContentList(feedContentDTOS));
    }

    @Override
    public boolean existsByUrl(String url) {
        return feedContentRepository.existsByUrl(url);
    }
}
