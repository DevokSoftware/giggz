package com.devok.giggz.restapi.mapper;

import org.mapstruct.Mapper;

import com.devok.giggz.openapi.model.NewsResponse;

import com.devok.giggz.service.dto.NewsDTO;


@Mapper(componentModel = "spring")
public interface NewsApiMapper {
    NewsResponse toNewsResponse(NewsDTO newsDTO);
}
