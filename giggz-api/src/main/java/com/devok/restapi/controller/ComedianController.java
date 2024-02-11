package com.devok.restapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.devok.restapi.api.ComediansApi;
import com.devok.restapi.mapper.ComedianMapper;
import com.devok.restapi.model.ComedianResponse;
import com.devok.service.ComedianService;
import com.devok.service.dto.ComedianDTO;

@RestController
public class ComedianController implements ComediansApi {
    private final ComedianService comedianService;
    private final ComedianMapper comedianMapper;

    public ComedianController(ComedianService comedianService, ComedianMapper comedianMapper) {
        this.comedianService = comedianService;
        this.comedianMapper = comedianMapper;
    }

    @Override
    public ResponseEntity<ComedianResponse> comediansComedianIdGet(Long comedianId) {
        ComedianDTO comedian = comedianService.getById(comedianId);
        return ResponseEntity.status(HttpStatus.OK).body(comedianMapper.toComedianResponse(comedian));
    }

    @Override
    public ResponseEntity<List<ComedianResponse>> comediansGet() {
        List<ComedianDTO> comedians = comedianService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(comedianMapper.toComedianResponseList(comedians));
    }
}
