package com.devok.giggz.restapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.devok.giggz.restapi.mapper.ComedianApiMapper;
import com.devok.giggz.openapi.api.ComediansApi;
import com.devok.giggz.openapi.model.ComedianResponse;
import com.devok.giggz.service.ComedianService;
import com.devok.giggz.service.dto.ComedianDTO;

@RestController
public class ComedianController implements ComediansApi {
    private final ComedianService comedianService;
    private final ComedianApiMapper comedianApiMapper;

    public ComedianController(ComedianService comedianService, ComedianApiMapper comedianApiMapper) {
        this.comedianService = comedianService;
        this.comedianApiMapper = comedianApiMapper;
    }

    @Override
    public ResponseEntity<ComedianResponse> comediansComedianIdGet(Long comedianId) {
        ComedianDTO comedian = comedianService.getById(comedianId);
        return ResponseEntity.status(HttpStatus.OK).body(comedianApiMapper.toComedianResponse(comedian));
    }

    @Override
    public ResponseEntity<List<ComedianResponse>> comediansGet() {
        List<ComedianDTO> comedians = comedianService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(comedianApiMapper.toComedianResponseList(comedians));
    }
}
