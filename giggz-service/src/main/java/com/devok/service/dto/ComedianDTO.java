package com.devok.service.dto;

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
}
