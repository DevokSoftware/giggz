package com.devok.giggz.service.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComedianDTO {
    private Long id;
    private String name;
    private String description;
    private String picture;
    private Integer priority;
    private String instagram;
    private String youtube;
    private String tiktok;
    private String twitter;
    private Set<ComedianContentDTO> contents;
}
