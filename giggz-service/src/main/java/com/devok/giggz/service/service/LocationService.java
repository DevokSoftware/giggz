package com.devok.giggz.service.service;

import java.util.List;

import com.devok.giggz.service.dto.LocationDTO;
import com.devok.giggz.service.filters.LocationsFilter;

public interface LocationService {
    List<LocationDTO> findAll(LocationsFilter locationsFilter);
    LocationDTO getById(long id);
    LocationDTO create(LocationDTO locationDTO);
    LocationDTO update(long id, LocationDTO locationDTO);
    void delete(long id);
}
