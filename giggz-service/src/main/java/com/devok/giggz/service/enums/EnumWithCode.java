package com.devok.giggz.service.enums;

public interface EnumWithCode {
    int getCode();

    default long getLongCode() {
        return (long) getCode();
    }
}
