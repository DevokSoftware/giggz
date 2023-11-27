package com.devok.giggz.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private int priority;
    private String url;
}
