package com.devok.giggz.service.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devok.giggz.service.service.LocationService;
import com.devok.giggz.service.dto.LocationDTO;
import com.devok.giggz.service.filters.LocationsFilter;
import com.devok.giggz.service.mapper.LocationMapper;
import com.devok.giggz.service.model.Location;
import com.devok.giggz.service.repository.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    public LocationServiceImpl(LocationRepository locationRepository, LocationMapper locationMapper) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    @Override
    public List<LocationDTO> findAll(LocationsFilter locationsFilter) {
        return locationMapper.toDtoList(locationRepository.findAllByFilters(
                locationsFilter.getName(),
                locationsFilter.getCity()
        ));
    }

    @Override
    public LocationDTO getById(long id) {
        return locationMapper.toDto(locationRepository.getReferenceById(id));
    }

    @Override
    public LocationDTO create(LocationDTO locationDTO) {
        Location location = locationRepository.save(locationMapper.toEntity(locationDTO));
        return locationMapper.toDto(location);
    }

    @Override
    public LocationDTO update(long id, LocationDTO updateLocation) {
        LocationDTO location = getById(id);
        locationMapper.updateValues(updateLocation, location);
        Location updatedLocation = locationRepository.save(locationMapper.toEntity(location));
        return locationMapper.toDto(updatedLocation);
    }

    @Override
    public void delete(long id) {
        locationRepository.delete(locationRepository.getReferenceById(id));
    }
}
