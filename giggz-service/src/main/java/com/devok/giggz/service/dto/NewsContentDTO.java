package com.devok.giggz.service.dto;

import java.util.List;

import com.devok.giggz.service.enums.ContentType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsContentDTO {
    private Long id;
    private ContentType contentType;
    private String url;
    private String name;
    private List<ComedianDTO> comedians;
}
