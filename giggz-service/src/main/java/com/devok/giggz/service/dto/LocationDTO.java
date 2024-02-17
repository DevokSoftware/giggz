package com.devok.giggz.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDTO {
    private long id;
    private String name;
    private String street;
    private String number;
    private String city;
}
