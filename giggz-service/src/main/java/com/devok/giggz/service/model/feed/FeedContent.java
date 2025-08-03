package com.devok.giggz.service.model.feed;

import com.devok.giggz.service.model.Comedian;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class FeedContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String source;
    private String url;
    private String thumbnailUrl;
    private String title;
    private String playlistId;
    private String description;
    private Integer duration;
    private LocalDateTime publishDate;
    private Boolean visible = true;

    @ManyToOne
    @JoinColumn(name = "feed_channel_id")
    private FeedChannel feedChannel;

    @ManyToMany
    @JoinTable(
            name = "FEED_CONTENT_COMEDIAN",
            joinColumns = @JoinColumn(name = "feed_content_id"),
            inverseJoinColumns = @JoinColumn(name = "comedian_id")
    )
    private Set<Comedian> comedians = new HashSet<>();
}
