package com.devok.giggz.service.repository.feed;

import com.devok.giggz.service.enums.SourceType;
import com.devok.giggz.service.model.feed.FeedChannel;
import com.devok.giggz.service.model.feed.FeedContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedChannelRepository extends JpaRepository<FeedChannel, Long> {
    List<FeedChannel> findAllBySource(SourceType sourceType);
}
