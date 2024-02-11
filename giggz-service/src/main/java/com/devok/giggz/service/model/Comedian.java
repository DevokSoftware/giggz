package com.devok.giggz.service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Comedian {
    @Id
    private long id;
    private String name;
    private String description;
    private String picture;
    private int priority;
    private String instagram;
    private String youtube;
    private String tiktok;
}
