package com.devok.giggz.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.devok.giggz.service.dto.standup.StandupDTO;
import com.devok.giggz.service.dto.standup.StandupInputDTO;
import com.devok.giggz.service.model.Standup;

@Mapper(componentModel = "spring")
public interface StandupMapper {
    StandupDTO toDto(Standup standup);
    List<StandupDTO> toDtoList(List<Standup> standups);
    Standup toEntity(StandupDTO standupDTO);
    @Mapping(target = "id", ignore = true)
    void updateValues(StandupDTO updatedStandup, @MappingTarget StandupDTO standupDTO);
    StandupDTO toDto(StandupInputDTO standupInputDTO);
    @Mapping(target = "id", ignore = true)
    void updateValues(StandupInputDTO standupInputDTO, @MappingTarget StandupDTO standupDTO);
}
