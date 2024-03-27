package com.devok.giggz.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.devok.giggz.service.dto.LocationDTO;
import com.devok.giggz.service.model.Location;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationDTO toDto(Location location);

    List<LocationDTO> toDtoList(List<Location> location);

    Location toEntity(LocationDTO location);

    @Mapping(target = "id", ignore = true)
    void updateValues(LocationDTO updatedLocation, @MappingTarget LocationDTO locationDTO);
}
