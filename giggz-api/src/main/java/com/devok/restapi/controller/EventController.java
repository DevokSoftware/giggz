package com.devok.restapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devok.restapi.dto.EventDTO;
import com.devok.restapi.service.EventService;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    List<EventDTO> findAllEvents() {
        return eventService.findAll();
    }
}
