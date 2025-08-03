package com.devok.giggz.service.repository.feed;

import com.devok.giggz.service.enums.SourceType;
import com.devok.giggz.service.model.feed.FeedChannel;
import com.devok.giggz.service.model.feed.FeedContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedChannelRepository extends JpaRepository<FeedChannel, Long> {
    List<FeedChannel> findAllBySource(SourceType sourceType);
}
