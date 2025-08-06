package com.devok.giggz.service.repository.feed;

import com.devok.giggz.service.model.feed.FeedContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedContentRepository extends JpaRepository<FeedContent, Long> {
    boolean existsByUrl(String url);

    Page<FeedContent> findAllByOrderByPublishDateDesc(Pageable pageable);
}
