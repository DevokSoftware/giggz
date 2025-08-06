package com.devok.giggz.service.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter
public abstract class EnumWithCodeAttributeConverter<E extends Enum<E> & EnumWithCode> implements AttributeConverter<E, Integer> {

    private final Class<E> enumClass;

    protected EnumWithCodeAttributeConverter(Class<E> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public Integer convertToDatabaseColumn(E attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public E convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(enumClass.getEnumConstants())
                .filter(e -> e.getCode() == dbData)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown code: " + dbData));
    }
}
