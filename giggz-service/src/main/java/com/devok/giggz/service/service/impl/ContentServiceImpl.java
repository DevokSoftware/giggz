package com.devok.giggz.service.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devok.giggz.service.service.ContentService;
import com.devok.giggz.service.dto.NewsContentDTO;
import com.devok.giggz.service.enums.ContentType;
import com.devok.giggz.service.mapper.ContentMapper;
import com.devok.giggz.service.repository.ContentRepository;

@Service
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;
    private final ContentMapper contentMapper;

    public ContentServiceImpl(ContentRepository contentRepository, ContentMapper contentMapper) {
        this.contentRepository = contentRepository;
        this.contentMapper = contentMapper;
    }

    @Override
    public List<NewsContentDTO> getLastYoutubeContentUpdates() {
        return contentMapper
                .toDTOList(contentRepository
                        .findContentsByIsTrendingIsTrueAndContentTypeOrderByCreatedAtDesc(ContentType.YOUTUBE));
    }

    @Override
    public List<NewsContentDTO> getLastPodcastsContentUpdates() {
        return contentMapper
                .toDTOList(contentRepository
                        .findContentsByIsTrendingIsTrueAndContentTypeOrderByCreatedAtDesc(ContentType.SPOTIFY));
    }
}
