package com.devok.giggz.restapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.devok.giggz.openapi.api.UsersApi;
import com.devok.giggz.openapi.model.EventResponse;
import com.devok.giggz.openapi.model.UserProfile;
import com.devok.giggz.restapi.mapper.EventApiMapper;
import com.devok.giggz.service.EventService;

@RestController
public class UserController implements UsersApi {

    private final EventService eventService;
    private final EventApiMapper eventApiMapper;

    public UserController(EventService eventService, EventApiMapper eventApiMapper) {
        this.eventService = eventService;
        this.eventApiMapper = eventApiMapper;
    }

    @Override
    public ResponseEntity<UserProfile> usersUserIdProfileGet(Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<List<EventResponse>> usersUserIdEventsAttendedGet(Long userId) {
        return ResponseEntity.ok(eventApiMapper.toEventsResponse(eventService.findAllByUser(userId)));
    }
}
