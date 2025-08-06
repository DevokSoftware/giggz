package com.devok.giggz.service.mapper;

import com.devok.giggz.service.enums.EnumWithCode;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class DefaultMapper {
    public Integer mapEnumWithCode(EnumWithCode source) {
        return source != null ? source.getCode() : null;
    }
}
