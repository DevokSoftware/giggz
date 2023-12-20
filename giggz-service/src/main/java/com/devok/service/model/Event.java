package com.devok.service.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Event {
    @Id
    private long id;
    private String name;
    private OffsetDateTime startDate;
    private String description;
    private String poster;
    private BigDecimal price;
    private int priority;
    private String url;
}
