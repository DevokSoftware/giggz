package com.devok.giggz.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.devok.giggz.service.dto.ComedianDTO;
import com.devok.giggz.service.model.Comedian;

@Mapper(componentModel = "spring")
public interface ComedianMapper {
    ComedianDTO toDto(Comedian comedian);

    List<ComedianDTO> toDtoList(List<Comedian> comedian);

    Comedian toEntity(ComedianDTO comedian);

    @Mapping(target = "events", ignore = true) // Ignore the events field
    void updateComedianFromDto(ComedianDTO dto, @MappingTarget Comedian entity);
}
