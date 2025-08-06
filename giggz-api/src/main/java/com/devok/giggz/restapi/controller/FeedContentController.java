package com.devok.giggz.restapi.controller;


import com.devok.giggz.openapi.api.FeedApi;
import com.devok.giggz.openapi.model.*;
import com.devok.giggz.restapi.mapper.FeedContentApiMapper;
import com.devok.giggz.service.dto.FeedContentDTO;
import com.devok.giggz.service.service.FeedContentService;
import com.devok.giggz.service.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedContentController implements FeedApi {
    private final FeedContentService feedContentService;
    private final FeedContentApiMapper feedContentApiMapper;

    public FeedContentController(FeedContentService feedContentService, FeedContentApiMapper feedContentApiMapper, UserService userService) {
        this.feedContentService = feedContentService;
        this.feedContentApiMapper = feedContentApiMapper;
    }

    @Override
    public ResponseEntity<PageFeedResponse> feedGet(Pageable pageable) {
        Page<FeedContentDTO> eventsPage = feedContentService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(feedContentApiMapper.toPageFeedResponse(eventsPage));
    }
}
