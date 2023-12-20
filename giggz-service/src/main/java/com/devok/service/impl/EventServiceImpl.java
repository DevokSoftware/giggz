package com.devok.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devok.service.dto.EventDTO;
import com.devok.service.mapper.EventMapper;
import com.devok.service.model.Event;
import com.devok.service.repository.EventRepository;
import com.devok.service.EventService;

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
        return eventMapper.toDtoList(eventRepository.findAll());
    }

    @Override
    public EventDTO create(EventDTO event) {
        Event createdEvent = eventRepository.save(eventMapper.toEntity(event));
        return eventMapper.toDto(createdEvent);
    }

    @Override
    public EventDTO update(EventDTO event) {
        Event updatedEvent = eventRepository.save(eventMapper.toEntity(event));
        return eventMapper.toDto(updatedEvent);
    }
}
