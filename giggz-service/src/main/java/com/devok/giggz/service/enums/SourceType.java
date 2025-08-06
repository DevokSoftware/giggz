package com.devok.giggz.service.enums;

import lombok.Getter;

@Getter
public enum SourceType implements EnumWithCode {
    SPOTIFY(1),
    YOUTUBE(2);

    private final int code;

    SourceType(int code) {
        this.code = code;
    }
}
