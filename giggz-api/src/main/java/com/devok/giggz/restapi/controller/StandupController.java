package com.devok.giggz.restapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.devok.giggz.openapi.api.StandupsApi;
import com.devok.giggz.openapi.model.ComediansGetFiltersParameter;
import com.devok.giggz.openapi.model.Standup;
import com.devok.giggz.openapi.model.StandupInput;
import com.devok.giggz.restapi.mapper.StandupApiMapper;
import com.devok.giggz.service.StandupService;

@RestController
public class StandupController implements StandupsApi {

    private final StandupService standupService;
    private final StandupApiMapper standupApiMapper;

    public StandupController(StandupService standupService, StandupApiMapper standupApiMapper) {
        this.standupService = standupService;
        this.standupApiMapper = standupApiMapper;
    }

    @Override
    public ResponseEntity<List<Standup>> standupsGet(ComediansGetFiltersParameter filters) {
        return null;
    }

    @Override
    public ResponseEntity<Standup> standupsPost(StandupInput standupInput) {
        return null;
    }

    @Override
    public ResponseEntity<Void> standupsStandupIdDelete(Long standupId) {
        return null;
    }

    @Override
    public ResponseEntity<Standup> standupsStandupIdGet(Long standupId) {
        return ResponseEntity.status(HttpStatus.OK).body(standupApiMapper.toStandupResponse(standupService.getById(standupId)));
    }

    @Override
    public ResponseEntity<Standup> standupsStandupIdPut(Long standupId, StandupInput standupInput) {
        return null;
    }
}
