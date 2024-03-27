package com.devok.giggz.restapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.devok.giggz.openapi.api.LocationsApi;
import com.devok.giggz.openapi.model.Location;
import com.devok.giggz.openapi.model.LocationInput;
import com.devok.giggz.openapi.model.LocationsGetFiltersParameter;
import com.devok.giggz.restapi.mapper.LocationsApiMapper;
import com.devok.giggz.service.LocationService;
import com.devok.giggz.service.dto.LocationDTO;

@RestController
public class LocationController implements LocationsApi {
    private final LocationService locationService;
    private final LocationsApiMapper locationsApiMapper;

    public LocationController(LocationService locationService, LocationsApiMapper locationsApiMapper) {
        this.locationService = locationService;
        this.locationsApiMapper = locationsApiMapper;
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Location>> locationsGet(LocationsGetFiltersParameter filters) {
        List<LocationDTO> locations = locationService.findAll(locationsApiMapper.toFilter(filters));
        return ResponseEntity.status(HttpStatus.OK).body(locationsApiMapper.toApi(locations));
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Location> locationsPost(LocationInput locationInput) {
        LocationDTO location = locationService.create(locationsApiMapper.toDto(locationInput));
        return ResponseEntity.status(HttpStatus.OK).body(locationsApiMapper.toApi(location));
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Location> locationsLocationIdGet(Long locationId) {
        LocationDTO location = locationService.getById(locationId);
        return ResponseEntity.status(HttpStatus.OK).body(locationsApiMapper.toApi(location));
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Location> locationsLocationIdPut(Long locationId, LocationInput locationInput) {
        LocationDTO location = locationService.update(locationId, locationsApiMapper.toDto(locationInput));
        return ResponseEntity.status(HttpStatus.OK).body(locationsApiMapper.toApi(location));
    }


    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> locationsLocationIdDelete(Long locationId) {
        locationService.delete(locationId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
