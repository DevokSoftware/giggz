package com.devok.giggz.restapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.devok.giggz.openapi.model.ComediansComedianIdEventsGetFiltersParameter;
import com.devok.giggz.openapi.model.ComediansGetFiltersParameter;
import com.devok.giggz.openapi.model.PageComedianEventsResponse;
import com.devok.giggz.openapi.model.PageComedianResponse;
import com.devok.giggz.restapi.mapper.ComedianApiMapper;
import com.devok.giggz.openapi.api.ComediansApi;
import com.devok.giggz.openapi.model.ComedianResponse;
import com.devok.giggz.service.ComedianService;
import com.devok.giggz.service.EventService;
import com.devok.giggz.service.dto.ComedianDTO;
import com.devok.giggz.service.dto.EventDTO;

@RestController
public class ComedianController implements ComediansApi {
    private final ComedianService comedianService;
    private final EventService eventService;
    private final ComedianApiMapper comedianApiMapper;

    public ComedianController(ComedianService comedianService, EventService eventService, ComedianApiMapper comedianApiMapper) {
        this.comedianService = comedianService;
        this.eventService = eventService;
        this.comedianApiMapper = comedianApiMapper;
    }

    @Override
    public ResponseEntity<ComedianResponse> comediansComedianIdGet(Long comedianId) {
        ComedianDTO comedian = comedianService.getById(comedianId);
        return ResponseEntity.status(HttpStatus.OK).body(comedianApiMapper.toComedianResponse(comedian));
    }

    @Override
    public ResponseEntity<PageComedianResponse> comediansGet(Pageable pageable, ComediansGetFiltersParameter filters) {
        Page<ComedianDTO> comediansPage = comedianService.findAll(pageable, comedianApiMapper.toComediansFilter(filters));
        return ResponseEntity.status(HttpStatus.OK).body(comedianApiMapper.toPageComedianResponse(comediansPage));
    }

    @Override
    public ResponseEntity<PageComedianEventsResponse> comediansComedianIdEventsGet(Long comedianId, Pageable pageable, ComediansComedianIdEventsGetFiltersParameter filters) {
        Page<EventDTO> comedianEventsPage = eventService.findAllByComedian(pageable, comedianId, filters.getDateFrom(), filters.getDateTo());
        return ResponseEntity.status(HttpStatus.OK).body(comedianApiMapper.toPageComedianEventsResponse(comedianEventsPage));
    }
}
