package com.devok.giggz.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devok.giggz.service.enums.ContentType;
import com.devok.giggz.service.model.Content;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findContentsByIsTrendingIsTrueAndContentTypeOrderByCreatedAtDesc(ContentType contentType);
}
