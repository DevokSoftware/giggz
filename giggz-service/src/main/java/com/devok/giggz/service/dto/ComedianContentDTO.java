package com.devok.giggz.service.dto;

import com.devok.giggz.service.enums.ContentType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComedianContentDTO {
    private Long id;
    private ContentType contentType;
    private String url;
    private String name;
    private Boolean isTrending;
    private String thumbnail;
}
