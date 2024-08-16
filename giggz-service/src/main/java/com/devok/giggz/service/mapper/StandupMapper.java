package com.devok.giggz.service.mapper;

import org.mapstruct.Mapper;

import com.devok.giggz.service.dto.StandupDTO;
import com.devok.giggz.service.model.Standup;

@Mapper(componentModel = "spring")
public interface StandupMapper {
    StandupDTO toDto(Standup standup);
}
