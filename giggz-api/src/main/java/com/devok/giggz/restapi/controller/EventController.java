package com.devok.giggz.restapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.devok.giggz.openapi.model.EventsGetFiltersParameter;
import com.devok.giggz.openapi.model.PageEventResponse;
import com.devok.giggz.restapi.mapper.EventApiMapper;
import com.devok.giggz.openapi.api.EventsApi;

import com.devok.giggz.openapi.model.CreateEventRequest;
import com.devok.giggz.openapi.model.EventResponse;
import com.devok.giggz.openapi.model.UpdateEventRequest;
import com.devok.giggz.service.dto.EventDTO;
import com.devok.giggz.service.EventService;

@RestController
public class EventController implements EventsApi {
    private final EventService eventService;
    private final EventApiMapper eventApiMapper;

    public EventController(EventService eventService, EventApiMapper eventApiMapper) {
        this.eventService = eventService;
        this.eventApiMapper = eventApiMapper;
    }

    @Override
    public ResponseEntity<PageEventResponse> eventsGet(Pageable pageable, EventsGetFiltersParameter filters) {
        Page<EventDTO> eventsPage = eventService.findAll(pageable, eventApiMapper.toEventsFilter(filters));
        return ResponseEntity.status(HttpStatus.OK).body(eventApiMapper.toPageEventResponse(eventsPage));
    }

    @Override
    public ResponseEntity<EventResponse> eventsPost(CreateEventRequest createEventRequest) {
        EventDTO createdEvent = eventService.create(eventApiMapper.toDTO(createEventRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(eventApiMapper.toEventResponse(createdEvent));
    }

    @Override
    public ResponseEntity<EventResponse> eventsEventIdGet(Long eventId) {
        EventDTO event = eventService.getById(eventId);
        return ResponseEntity.status(HttpStatus.OK).body(eventApiMapper.toEventResponse(event));
    }

    @Override
    public ResponseEntity<EventResponse> eventsEventIdPut(Long eventId, UpdateEventRequest updateEventRequest) {
        EventDTO updatedEvent = eventService.update(eventId, eventApiMapper.toDTO(updateEventRequest));
        return ResponseEntity.status(HttpStatus.OK).body(eventApiMapper.toEventResponse(updatedEvent));
    }
}
