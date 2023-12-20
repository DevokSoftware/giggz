package com.devok.service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Place {
    @Id
    private long id;
    private String name;
    private String street;
    private String number;
    private String city;
}
