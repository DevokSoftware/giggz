package com.devok.giggz.restapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import com.devok.giggz.openapi.api.MeApi;
import com.devok.giggz.openapi.api.UsersApi;
import com.devok.giggz.openapi.model.EventResponse;
import com.devok.giggz.openapi.model.UserProfile;
import com.devok.giggz.restapi.mapper.EventApiMapper;
import com.devok.giggz.service.EventService;
import com.devok.giggz.service.auth.UserPrincipal;

@RestController
public class UserController implements UsersApi, MeApi {

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
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<EventResponse>> usersUserIdEventsAttendedGet(Long userId) {
        return ResponseEntity.ok(eventApiMapper.toEventsResponse(eventService.findAllByUser(userId)));
    }

    @Override
    public ResponseEntity<List<EventResponse>> meEventsAttendedGet() {
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(eventApiMapper.toEventsResponse(eventService.findAllByUser(user.getId())));
    }
}
