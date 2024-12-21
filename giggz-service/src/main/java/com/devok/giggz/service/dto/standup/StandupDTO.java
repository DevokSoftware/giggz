package com.devok.giggz.service.dto.standup;

import com.devok.giggz.service.dto.ComedianDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandupDTO {
    private Long id;
    private String name;
    private String poster;
    private String description;
    private ComedianDTO comedian;
    private Boolean isTrending;
}
