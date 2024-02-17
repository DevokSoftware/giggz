package com.devok.giggz.service.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private OffsetDateTime date;
    private String description;
    private String poster;
    private BigDecimal price;
    private Integer priority;
    private String url;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location")
    private Location location;
    @ManyToMany(mappedBy = "events")
    Set<Comedian> comedians;
}
