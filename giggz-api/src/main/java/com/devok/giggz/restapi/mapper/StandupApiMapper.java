package com.devok.giggz.restapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.devok.giggz.openapi.model.Standup;
import com.devok.giggz.openapi.model.StandupInput;
import com.devok.giggz.service.dto.standup.StandupDTO;
import com.devok.giggz.service.dto.standup.StandupInputDTO;

@Mapper(componentModel = "spring")
public interface StandupApiMapper {
    //StandupDTO toDto(StandupInput standupInput);
    List<Standup> toApi(List<StandupDTO> standupDTOS);
    Standup toApi(StandupDTO standupDTO);
    StandupInputDTO toDto (StandupInput standupInput);
}
