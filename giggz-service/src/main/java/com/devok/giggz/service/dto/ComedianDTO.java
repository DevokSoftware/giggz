package com.devok.giggz.service.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComedianDTO {
    private long id;
    private String name;
    private String description;
    private String city;
    private String picture;
    private int priority;
    private String instagram;
    private String youtube;
    private String tiktok;
    private String twitter;
    private Set<EventDTO> events;
    private Set<ComedianContentDTO> contents;
}
