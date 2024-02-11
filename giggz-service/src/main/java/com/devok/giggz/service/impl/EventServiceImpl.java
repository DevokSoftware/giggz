package com.devok.giggz.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devok.giggz.service.dto.EventDTO;
import com.devok.giggz.service.mapper.EventMapper;
import com.devok.giggz.service.model.Event;
import com.devok.giggz.service.repository.EventRepository;
import com.devok.giggz.service.EventService;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public EventDTO getById(long id) {
        return eventMapper.toDto(eventRepository.getReferenceById(id));
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
    public EventDTO update(long eventId, EventDTO updatedEvent) {
        EventDTO eventDto = getById(eventId);
        eventMapper.updateValues(updatedEvent, eventDto);
        Event updatedEntity = eventRepository.save(eventMapper.toEntity(eventDto));
        return eventMapper.toDto(updatedEntity);
    }
}
