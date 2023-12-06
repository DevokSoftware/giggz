package com.devok.restapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devok.restapi.dto.EventDTO;
import com.devok.restapi.mapper.EventMapper;
import com.devok.restapi.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public List<EventDTO> findAll() {
        return eventMapper.map(eventRepository.findAll());
    }
}
