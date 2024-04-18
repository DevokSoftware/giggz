package com.devok.giggz.restapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.devok.giggz.openapi.model.Location;
import com.devok.giggz.openapi.model.LocationInput;
import com.devok.giggz.openapi.model.LocationsGetFiltersParameter;
import com.devok.giggz.service.dto.LocationDTO;
import com.devok.giggz.service.filters.LocationsFilter;

@Mapper(componentModel = "spring")
public interface LocationsApiMapper {
    LocationDTO toDto(LocationInput locationInput);
    List<Location> toApi(List<LocationDTO> locations);
    Location toApi(LocationDTO location);
    LocationsFilter toFilter(LocationsGetFiltersParameter eventsGetFiltersParameter);
}
