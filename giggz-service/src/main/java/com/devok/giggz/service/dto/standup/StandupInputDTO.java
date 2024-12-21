package com.devok.giggz.service.dto.standup;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandupInputDTO {
    private Long id;
    private String name;
    private String poster;
    private String description;
    private Long comedianId;
    private Boolean isTrending;
}
