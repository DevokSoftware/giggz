package com.devok.giggz.restapi.mapper;

import org.mapstruct.Mapper;

import com.devok.giggz.openapi.model.Standup;
import com.devok.giggz.service.dto.StandupDTO;

@Mapper(componentModel = "spring")
public interface StandupApiMapper {
    Standup toStandupResponse(StandupDTO standupDTO);
}
