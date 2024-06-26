package com.devok.giggz.service.impl;

import java.time.OffsetDateTime;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devok.giggz.service.ComedianService;
import com.devok.giggz.service.LocationService;
import com.devok.giggz.service.dto.LocationDTO;
import com.devok.giggz.service.dto.event.CreateEventDTO;
import com.devok.giggz.service.dto.event.EventDTO;
import com.devok.giggz.service.dto.event.UpdateEventDTO;
import com.devok.giggz.service.filters.EventsFilter;
import com.devok.giggz.service.mapper.EventMapper;
import com.devok.giggz.service.model.Comedian;
import com.devok.giggz.service.model.Event;
import com.devok.giggz.service.repository.EventRepository;
import com.devok.giggz.service.EventService;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final LocationService locationService;
    private final ComedianService comedianService;

    public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper, LocationService locationService, ComedianService comedianService) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.locationService = locationService;
        this.comedianService = comedianService;
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
    public EventDTO create(CreateEventDTO createEvent) {
        EventDTO eventDto = eventMapper.toEntity(createEvent);
        eventDto.setPriority(1); //TODO Fix this in DB
        updateLocation(createEvent.getLocationId(), eventDto);
        Event createdEvent = eventMapper.toEntity(eventDto);
        updateComedians(createEvent.getComedianIds(), createdEvent);
        eventRepository.save(createdEvent);
        return eventMapper.toDto(createdEvent);
    }

    @Override
    public EventDTO update(long eventId, UpdateEventDTO updatedEvent) {
        EventDTO eventDto = getById(eventId);
        eventMapper.updateValues(updatedEvent, eventDto);
        updateLocation(updatedEvent.getLocationId(), eventDto);
        Event updatedEntity = eventMapper.toEntity(eventDto);
        updatedEntity.setPriority(1); //TODO Fix this in DB
        updateComedians(updatedEvent.getComedianIds(), updatedEntity);
        eventRepository.save(updatedEntity);
        return eventMapper.toDto(updatedEntity);
    }

    @Override
    public void delete(long id) {
        Event event = eventRepository.getReferenceById(id);
        event.removeEventFromComedians();
        eventRepository.delete(event);
    }

    private void updateLocation(Long locationId, EventDTO eventDto) {
        if (locationId != null) {
            LocationDTO locationDTO = locationService.getById(locationId);
            eventDto.setLocation(locationDTO);
        }
    }

    private void updateComedians(Set<Long> comedianIds, Event event) {
        if (comedianIds != null) {
            for (Long comedianId : comedianIds) {
                Comedian comedian = comedianService.getEntity(comedianId);
                event.addComedian(comedian);
            }
        }
    }
}
