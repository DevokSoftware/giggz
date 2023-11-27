package com.devok.giggz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Comedian {
    @Id
    private long id;
    private String name;
    private String photo;
    private int priority;
}
