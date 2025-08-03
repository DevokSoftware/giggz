package com.devok.giggz.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LocationDTO {
    private Long id;
    private String name;
    private String street;
    private String number;
    private String city;
}
