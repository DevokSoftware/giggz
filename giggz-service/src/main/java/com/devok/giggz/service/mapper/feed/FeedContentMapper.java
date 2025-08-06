package com.devok.giggz.service.mapper.feed;

import com.devok.giggz.service.dto.FeedContentDTO;
import com.devok.giggz.service.mapper.DefaultMapper;
import com.devok.giggz.service.model.feed.FeedContent;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = DefaultMapper.class)
public interface FeedContentMapper {
    FeedContent toFeedContent(FeedContentDTO feedContentDTO);

    List<FeedContent> toFeedContentList(List<FeedContentDTO> feedContentDTOList);

    FeedContentDTO toDto(FeedContent feedContent);
}