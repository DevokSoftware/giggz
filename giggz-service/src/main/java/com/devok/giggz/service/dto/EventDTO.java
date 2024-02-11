package com.devok.giggz.service.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventDTO {
    private long id;
    private String name;
    private OffsetDateTime startDate;
    private String description;
    private String poster;
    private BigDecimal price;
    private int priority;
    private String url;
}
