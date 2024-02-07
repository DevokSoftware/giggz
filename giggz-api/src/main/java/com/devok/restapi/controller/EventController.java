package com.devok.restapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.devok.restapi.api.EventsApi;
import com.devok.restapi.mapper.EventMapper;

import com.devok.restapi.model.CreateEventRequest;
import com.devok.restapi.model.EventResponse;
import com.devok.restapi.model.UpdateEventRequest;
import com.devok.service.dto.EventDTO;
import com.devok.service.EventService;

@RestController
public class EventController implements EventsApi {
    private final EventService eventService;
    private final EventMapper eventMapper;

    public EventController(EventService eventService, EventMapper eventMapper) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
    }

    @Override
    public ResponseEntity<List<EventResponse>> eventsGet() {
        List<EventDTO> events = eventService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(eventMapper.toEventResponseList(events));
    }

    @Override
    public ResponseEntity<EventResponse> eventsPost(CreateEventRequest createEventRequest) {
        EventDTO createdEvent = eventService.create(eventMapper.toDTO(createEventRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(eventMapper.toEventResponse(createdEvent));
    }

    @Override
    public ResponseEntity<EventResponse> eventsEventIdGet(Long eventId) {
        EventDTO event = eventService.getById(eventId);
        return ResponseEntity.status(HttpStatus.OK).body(eventMapper.toEventResponse(event));
    }

    @Override
    public ResponseEntity<EventResponse> eventsEventIdPut(Long eventId, UpdateEventRequest updateEventRequest) {
        EventDTO updatedEvent = eventService.update(eventId, eventMapper.toDTO(updateEventRequest));
        return ResponseEntity.status(HttpStatus.OK).body(eventMapper.toEventResponse(updatedEvent));
    }
}
