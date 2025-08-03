package com.devok.giggz.service.dto;

import com.devok.giggz.service.enums.SourceType;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class FeedContentDTO {
    private SourceType source;
    private String channelId;
    private String url;
    private String thumbnailUrl;
    private String title;
    private OffsetDateTime addedDate;
    private String playlistId;
}
