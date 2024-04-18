package com.devok.giggz.service.dto.event;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

import com.devok.giggz.service.dto.ComedianDTO;
import com.devok.giggz.service.dto.LocationDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventDTO {
    private Long id;
    private String name;
    private OffsetDateTime date;
    private String description;
    private String poster;
    private BigDecimal price;
    private Integer priority;
    private String url;
    private LocationDTO location;
    private Set<ComedianDTO> comedians;
}
