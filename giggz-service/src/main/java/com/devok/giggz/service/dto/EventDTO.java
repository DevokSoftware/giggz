package com.devok.giggz.service.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

import com.devok.giggz.service.model.Comedian;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventDTO {
    private long id;
    private String name;
    private OffsetDateTime date;
    private String description;
    private String poster;
    private BigDecimal price;
    private int priority;
    private String url;
    private LocationDTO location;
    private Set<Comedian> comedians;
}
