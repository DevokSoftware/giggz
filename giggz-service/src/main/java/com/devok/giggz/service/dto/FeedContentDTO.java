package com.devok.giggz.service.dto;

import com.devok.giggz.service.enums.SourceType;
import com.devok.giggz.service.model.feed.FeedChannel;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class FeedContentDTO {
    private SourceType source;
    private FeedChannel feedChannel;
    private String url;
    private String thumbnailUrl;
    private String title;
    private OffsetDateTime publishDate;
    private String playlistId;
    private String description;
    private Integer duration;
}
