package com.devok.giggz.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devok.giggz.dto.EventDTO;
import com.devok.giggz.mapper.EventMapper;
import com.devok.giggz.repository.EventRepository;

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
        return eventMapper.eventsToEventDTOs(eventRepository.findAll());
    }
}
