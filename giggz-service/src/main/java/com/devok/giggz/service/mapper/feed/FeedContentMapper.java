package com.devok.giggz.service.mapper.feed;

import com.devok.giggz.service.dto.FeedContentDTO;
import com.devok.giggz.service.model.feed.FeedChannel;
import com.devok.giggz.service.model.feed.FeedContent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface FeedContentMapper {

    @Mapping(source = "channelId", target = "feedChannel", qualifiedByName = "fromId")
    FeedContent toFeedContent(FeedContentDTO feedContentDTO);

    List<FeedContent> toFeedContentList(List<FeedContentDTO> feedContentDTOList);

    @Named("fromId")
    default FeedChannel fromId(Long id) {
        if (id == null) return null;
        FeedChannel channel = new FeedChannel();
        channel.setId(id);
        return channel;
    }
}