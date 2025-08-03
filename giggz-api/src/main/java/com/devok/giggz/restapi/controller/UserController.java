package com.devok.giggz.restapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import com.devok.giggz.openapi.api.MeApi;
import com.devok.giggz.openapi.api.UsersApi;
import com.devok.giggz.openapi.model.ComedianResponse;
import com.devok.giggz.openapi.model.EventResponse;
import com.devok.giggz.openapi.model.UserProfile;
import com.devok.giggz.restapi.mapper.ComedianApiMapper;
import com.devok.giggz.restapi.mapper.EventApiMapper;
import com.devok.giggz.restapi.mapper.UserApiMapper;
import com.devok.giggz.service.service.ComedianService;
import com.devok.giggz.service.service.EventService;
import com.devok.giggz.service.service.UserService;
import com.devok.giggz.service.auth.UserPrincipal;

@RestController
public class UserController implements UsersApi, MeApi {

    private final EventService eventService;
    private final EventApiMapper eventApiMapper;
    private final ComedianService comedianService;
    private final ComedianApiMapper comedianApiMapper;
    private final UserService userService;
    private final UserApiMapper userApiMapper;

    public UserController(EventService eventService,
                          EventApiMapper eventApiMapper,
                          ComedianService comedianService,
                          ComedianApiMapper comedianApiMapper,
                          UserService userService,
                          UserApiMapper userApiMapper) {
        this.eventService = eventService;
        this.eventApiMapper = eventApiMapper;
        this.comedianService = comedianService;
        this.comedianApiMapper = comedianApiMapper;
        this.userService = userService;
        this.userApiMapper = userApiMapper;
    }

    @Override
    public ResponseEntity<UserProfile> usersUserIdProfileGet(Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<List<EventResponse>> usersUserIdEventsAttendedGet(Long userId) {
        return ResponseEntity.ok(eventApiMapper.toEventsResponse(eventService.findAllByUser(userId)));
    }

    @Override
    public ResponseEntity<List<ComedianResponse>> usersUserIdComediansFavoritesGet(Long userId) {
        return ResponseEntity.ok(comedianApiMapper.toComedianResponseList(comedianService.findFavoriteByUser(userId)));
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<EventResponse>> meEventsAttendedGet() {
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(eventApiMapper.toEventsResponse(eventService.findAllByUser(user.getId())));
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserProfile> meProfileGet() {
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(userApiMapper.toApi(userService.findById(user.getId())));
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ComedianResponse>> meComediansFavoritesGet() {
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(comedianApiMapper.toComedianResponseList(comedianService.findFavoriteByUser(user.getId())));
    }
}
