package com.devok.giggz.service.impl;

import java.time.OffsetDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devok.giggz.service.dto.EventDTO;
import com.devok.giggz.service.filters.EventsFilter;
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
    public Page<EventDTO> findAll(Pageable pageable, EventsFilter eventsFilter) {
        return eventRepository.findAllByFilters(
                eventsFilter.getName(),
                eventsFilter.getComedianId(),
                eventsFilter.getCity(),
                eventsFilter.getDateFrom(),
                eventsFilter.getDateTo(),
                pageable).map(eventMapper::toDto);
    }

    @Override
    public Page<EventDTO> findAllByComedian(Pageable pageable, Long comedianId, OffsetDateTime dateFrom, OffsetDateTime dateTo) {
        return eventRepository.findAllByFilters(
                null,
                comedianId,
                null,
                dateFrom,
                dateTo,
                pageable).map(eventMapper::toDto);
    }

    @Override
    public EventDTO create(EventDTO event) {
        //TODO Fix this in DB
        event.setPriority(1);
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

    @Override
    public void delete(long id) {
        eventRepository.delete(eventRepository.getReferenceById(id));
    }
}
