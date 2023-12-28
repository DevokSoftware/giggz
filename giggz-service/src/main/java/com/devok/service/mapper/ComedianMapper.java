package com.devok.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.devok.service.dto.ComedianDTO;
import com.devok.service.model.Comedian;

@Mapper(componentModel = "spring")
public interface ComedianMapper {
    ComedianDTO toDto(Comedian comedian);

    List<ComedianDTO> toDtoList(List<Comedian> comedian);

    Comedian toEntity(ComedianDTO comedian);

    void updateValues(ComedianDTO updatedComedian, @MappingTarget ComedianDTO comedianDto);
}
