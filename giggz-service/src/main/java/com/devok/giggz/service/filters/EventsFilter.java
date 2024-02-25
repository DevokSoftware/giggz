package com.devok.giggz.service.filters;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventsFilter {
    private String name;
    private Long comedianId;
    private String city;
    private OffsetDateTime dateFrom;
    private OffsetDateTime dateTo;
}
