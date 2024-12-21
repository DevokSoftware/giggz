package com.devok.giggz.restapi.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.devok.giggz.openapi.api.NewsApi;
import com.devok.giggz.openapi.model.ContentResponse;
import com.devok.giggz.openapi.model.ContentType;
import com.devok.giggz.openapi.model.NewsResponse;
import com.devok.giggz.openapi.model.Standup;
import com.devok.giggz.restapi.mapper.NewsApiMapper;
import com.devok.giggz.service.NewsService;

@RestController
public class NewsController implements NewsApi {
    private final NewsService newsService;
    private final NewsApiMapper newsApiMapper;

    public NewsController(NewsService newsService, NewsApiMapper newsApiMapper) {
        this.newsService = newsService;
        this.newsApiMapper = newsApiMapper;
    }

    @Override
    public ResponseEntity<NewsResponse> newsGet() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(newsApiMapper.toNewsResponse(newsService.getNews()));
    }
}
