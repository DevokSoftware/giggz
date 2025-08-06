package com.devok.giggz.service.repository.feed;

import com.devok.giggz.service.model.feed.FeedContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedContentRepository extends JpaRepository<FeedContent, Long> {
    boolean existsByUrl(String url);
}
