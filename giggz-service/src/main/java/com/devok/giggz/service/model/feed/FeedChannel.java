package com.devok.giggz.service.model.feed;

import com.devok.giggz.service.enums.SourceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class FeedChannel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String channelId;
    private SourceType source;
    private OffsetDateTime lastSync;
    private Integer syncPeriod;
    @OneToMany(mappedBy = "feedChannel")
    private List<FeedContent> contents;
}
