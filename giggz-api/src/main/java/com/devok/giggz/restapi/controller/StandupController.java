package com.devok.giggz.restapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.devok.giggz.openapi.api.StandupsApi;
import com.devok.giggz.openapi.model.Standup;
import com.devok.giggz.openapi.model.StandupInput;
import com.devok.giggz.restapi.mapper.StandupApiMapper;
import com.devok.giggz.service.StandupService;
import com.devok.giggz.service.dto.StandupDTO;

@RestController
public class StandupController implements StandupsApi {

    private final StandupService standupService;
    private final StandupApiMapper standupApiMapper;

    public StandupController(StandupService standupService, StandupApiMapper standupApiMapper) {
        this.standupService = standupService;
        this.standupApiMapper = standupApiMapper;
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Standup>> standupsGet() {
        List<StandupDTO> standups = standupService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(standupApiMapper.toApi(standups));
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Standup> standupsPost(StandupInput standupInput) {
        StandupDTO standupDTO = standupService.create(standupApiMapper.toDto(standupInput));
        return ResponseEntity.status(HttpStatus.OK).body(standupApiMapper.toApi(standupDTO));
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> standupsStandupIdDelete(Long standupId) {
        standupService.delete(standupId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Standup> standupsStandupIdGet(Long standupId) {
        return ResponseEntity.status(HttpStatus.OK).body(standupApiMapper.toApi(standupService.getById(standupId)));
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Standup> standupsStandupIdPut(Long standupId, StandupInput standupInput) {
        StandupDTO standupDTO = standupService.update(standupId, standupApiMapper.toDto(standupInput));
        return ResponseEntity.status(HttpStatus.OK).body(standupApiMapper.toApi(standupDTO));
    }
}
