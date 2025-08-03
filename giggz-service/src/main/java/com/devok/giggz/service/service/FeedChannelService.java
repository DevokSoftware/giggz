package com.devok.giggz.service.service;

import com.devok.giggz.service.enums.SourceType;
import com.devok.giggz.service.model.feed.FeedChannel;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;

public interface FeedChannelService {
    List<FeedChannel> getAllChannelsBySource(SourceType source);
    void updateLastSync(FeedChannel channel, OffsetDateTime newSyncTime);
}
