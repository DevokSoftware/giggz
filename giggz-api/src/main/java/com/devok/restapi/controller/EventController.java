package com.devok.restapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devok.restapi.api.EventApi;
import com.devok.restapi.mapper.EventMapper;
import com.devok.restapi.model.CreateEventRequest;
import com.devok.restapi.model.EventResponse;
import com.devok.service.dto.EventDTO;
import com.devok.service.EventService;

@RestController
@RequestMapping("/events")
public class EventController implements EventApi {
    private final EventService eventService;
    private final EventMapper eventMapper;

    public EventController(EventService eventService, EventMapper eventMapper) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
    }

    @GetMapping
    List<EventDTO> findAllEvents() {
        return eventService.findAll();
    }

    @Override
    public ResponseEntity<EventResponse> eventPost(CreateEventRequest createEventRequest) {
        EventDTO createdEvent = eventService.create(eventMapper.toDTO(createEventRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(eventMapper.toEventResponse(createdEvent));
    }
}
