package com.devok.giggz.restapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import com.devok.giggz.openapi.model.AttendedEventInput;
import com.devok.giggz.openapi.model.EventsGetFiltersParameter;
import com.devok.giggz.openapi.model.PageEventResponse;
import com.devok.giggz.restapi.mapper.EventApiMapper;
import com.devok.giggz.openapi.api.EventsApi;

import com.devok.giggz.openapi.model.CreateEventRequest;
import com.devok.giggz.openapi.model.EventResponse;
import com.devok.giggz.openapi.model.UpdateEventRequest;
import com.devok.giggz.service.UserService;
import com.devok.giggz.service.auth.UserPrincipal;
import com.devok.giggz.service.dto.event.EventDTO;
import com.devok.giggz.service.EventService;

@RestController
public class EventController implements EventsApi {
    private final EventService eventService;
    private final EventApiMapper eventApiMapper;
    private final UserService userService;

    public EventController(EventService eventService, EventApiMapper eventApiMapper, UserService userService) {
        this.eventService = eventService;
        this.eventApiMapper = eventApiMapper;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<PageEventResponse> eventsGet(Pageable pageable, EventsGetFiltersParameter filters) {
        Page<EventDTO> eventsPage = eventService.findAll(pageable, eventApiMapper.toEventsFilter(filters));
        return ResponseEntity.status(HttpStatus.OK).body(eventApiMapper.toPageEventResponse(eventsPage));
    }

    @Override
    @PreAuthorize("isAuthenticated() && hasRole('ROLE_ADMIN')")
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
    @PreAuthorize("isAuthenticated() && hasRole('ROLE_ADMIN')")
    public ResponseEntity<EventResponse> eventsEventIdPut(Long eventId, UpdateEventRequest updateEventRequest) {
        EventDTO updatedEvent = eventService.update(eventId, eventApiMapper.toDTO(updateEventRequest));
        return ResponseEntity.status(HttpStatus.OK).body(eventApiMapper.toEventResponse(updatedEvent));
    }

    @Override
    @PreAuthorize("isAuthenticated() && hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> eventsEventIdDelete(Long eventId) {
        eventService.delete(eventId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<EventResponse> eventsEventIdAttendedPost(Long eventId, AttendedEventInput attendedEventInput) {
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        EventDTO eventDTO = eventService.attendedEventByUser(user.getId(), eventId, attendedEventInput.getIsAttended());
        return ResponseEntity.status(HttpStatus.OK).body(eventApiMapper.toEventResponse(eventDTO));
    }
}
