package com.devok.giggz.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devok.giggz.dto.EventDTO;
import com.devok.giggz.service.EventService;

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
