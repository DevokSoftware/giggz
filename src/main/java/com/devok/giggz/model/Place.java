package com.devok.giggz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Place {
    @Id
    private long id;
    private String name;
    private String street;
    private String number;
    private String city;
}
