package com.devok.giggz.restapi.mapper;

import com.devok.giggz.openapi.model.PageFeedResponse;
import com.devok.giggz.service.dto.FeedContentDTO;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface FeedContentApiMapper {
    PageFeedResponse toPageFeedResponse(Page<FeedContentDTO> feedContentPage);
}
