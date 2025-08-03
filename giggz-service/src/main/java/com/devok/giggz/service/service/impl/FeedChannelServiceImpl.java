package com.devok.giggz.service.service.impl;

import com.devok.giggz.service.enums.SourceType;
import com.devok.giggz.service.model.feed.FeedChannel;
import com.devok.giggz.service.repository.feed.FeedChannelRepository;
import com.devok.giggz.service.service.FeedChannelService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class FeedChannelServiceImpl implements FeedChannelService {
    private final FeedChannelRepository repository;

    public FeedChannelServiceImpl(FeedChannelRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FeedChannel> getAllChannelsBySource(SourceType source) {
        return repository.findAllBySource(source);
    }

    @Override
    public void updateLastSync(FeedChannel channel, OffsetDateTime newSyncTime) {
        channel.setLastSync(newSyncTime);
        repository.save(channel);
    }
}
