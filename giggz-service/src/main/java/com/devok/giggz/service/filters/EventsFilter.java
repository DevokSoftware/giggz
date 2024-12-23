package com.devok.giggz.service.filters;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EventsFilter {
    private String name;
    private Long comedianId;
    private String city;
    private OffsetDateTime dateFrom;
    private OffsetDateTime dateTo;
    private Long standupId;
}
