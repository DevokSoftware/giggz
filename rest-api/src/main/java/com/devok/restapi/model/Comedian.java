package com.devok.restapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Comedian {
    @Id
    private long id;
    private String name;
    private String photo;
    private int priority;
}
