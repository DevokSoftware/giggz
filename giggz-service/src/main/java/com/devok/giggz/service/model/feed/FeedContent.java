package com.devok.giggz.service.model.feed;

import com.devok.giggz.service.enums.SourceType;
import com.devok.giggz.service.model.Comedian;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(
        indexes = {
                @Index(name = "idx_feed_content_url", columnList = "url")
        }
)
public class FeedContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Convert(converter = SourceType.Converter.class)
    private SourceType source;
    @Column(nullable = false, unique = true)
    private String url;
    private String thumbnailUrl;
    private String title;
    private String playlistId;
    @Lob
    private String description;
    private Integer duration;
    private OffsetDateTime publishDate;
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
