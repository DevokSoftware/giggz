package com.devok.giggz.service.enums;

import jakarta.persistence.Convert;
import lombok.Getter;

@Getter
public enum SourceType implements EnumWithCode {
    SPOTIFY(1),
    YOUTUBE(2);

    private final int code;

    SourceType(int code) {
        this.code = code;
    }

    public static class Converter extends EnumWithCodeAttributeConverter<SourceType> {
        public Converter() {
            super(SourceType.class);
        }
    }
}
