package com.devok.giggz.service.enums;

import lombok.Getter;

@Getter
public enum SourceType {
    SPOTIFY(1),
    YOUTUBE(1);

    private Integer value;

    SourceType(Integer value) {
    }
}
