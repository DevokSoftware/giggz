package com.devok.giggz.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.devok.giggz.service.dto.ComedianContentDTO;
import com.devok.giggz.service.dto.NewsContentDTO;
import com.devok.giggz.service.model.Content;

@Mapper(componentModel = "spring")
public interface ContentMapper {
    NewsContentDTO toDTO(Content content);
    List<NewsContentDTO> toDTOList(List<Content> contents);
}
