package com.devok.giggz.service.dto.event;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEventDTO {
    private String name;
    private OffsetDateTime date;
    private String description;
    private String poster;
    private BigDecimal price;
    private Integer priority;
    private String url;
    private Long locationId;
    private Set<Long> comedianIds;
}
