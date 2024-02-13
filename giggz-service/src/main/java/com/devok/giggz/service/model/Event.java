package com.devok.giggz.service.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Event {
    @Id
    private long id;
    private String name;
    private OffsetDateTime date;
    private String description;
    private String poster;
    private BigDecimal price;
    private Integer priority;
    private String url;
    @ManyToMany(mappedBy = "events")
    Set<Comedian> comedians;
}
