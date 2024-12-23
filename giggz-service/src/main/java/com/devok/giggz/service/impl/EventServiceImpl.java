package com.devok.giggz.service.impl;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devok.giggz.service.ComedianService;
import com.devok.giggz.service.LocationService;
import com.devok.giggz.service.StandupService;
import com.devok.giggz.service.UserService;
import com.devok.giggz.service.dto.LocationDTO;
import com.devok.giggz.service.dto.standup.StandupDTO;
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
    private final StandupService standupService;
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

    public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper, LocationService locationService,
                            ComedianService comedianService, StandupService standupService, UserService userService) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.locationService = locationService;
        this.comedianService = comedianService;
        this.standupService = standupService;
        this.userService = userService;
    }

    @Override
    public EventDTO getById(long id) {
        return eventMapper.toDto(eventRepository.getReferenceById(id));
    }

    @Override
    public Page<EventDTO> findAll(Pageable pageable, EventsFilter eventsFilter) {
        logger.info("Fetching events with filters: {}", eventsFilter);
        return eventRepository.findAllByFilters(
                eventsFilter.getName(),
                eventsFilter.getComedianId(),
                eventsFilter.getCity(),
                eventsFilter.getDateFrom(),
                eventsFilter.getDateTo(),
                eventsFilter.getStandupId(),
                pageable).map(eventMapper::toDto);
    }

    @Override
    public Page<EventDTO> findAllByComedian(Pageable pageable, Long comedianId, OffsetDateTime dateFrom, OffsetDateTime dateTo) {
        logger.info("Fetching events for comedian with {}, between {} and {}", comedianId, dateFrom, dateTo);
        return eventRepository.findAllByFilters(
                null,
                comedianId,
                null,
                dateFrom,
                dateTo,
                null,
                pageable).map(eventMapper::toDto);
    }

    @Override
    public List<EventDTO> findAllByUser(long userId) {
        return eventRepository.findEventsByUserId(userId).stream().map(eventMapper::toDto).toList();
    }

    @Override
    public EventDTO create(CreateEventDTO createEvent) {
        EventDTO eventDto = eventMapper.toEntity(createEvent);
        eventDto.setPriority(1); //TODO Fix this in DB
        setLocation(createEvent.getLocationId(), eventDto);
        setStandup(createEvent.getStandupId(), eventDto);
        Event createdEvent = eventMapper.toEntity(eventDto);
        setComedians(createEvent.getComedianIds(), createdEvent);
        eventRepository.save(createdEvent);
        return eventMapper.toDto(createdEvent);
    }

    @Override
    public EventDTO update(long eventId, UpdateEventDTO updatedEvent) {
        EventDTO eventDto = getById(eventId);
        eventMapper.updateValues(updatedEvent, eventDto);
        setLocation(updatedEvent.getLocationId(), eventDto);
        setStandup(updatedEvent.getStandupId(), eventDto);
        Event updatedEntity = eventMapper.toEntity(eventDto);
        updatedEntity.setPriority(1); //TODO Fix this in DB
        setComedians(updatedEvent.getComedianIds(), updatedEntity);
        eventRepository.save(updatedEntity);
        return eventMapper.toDto(updatedEntity);
    }

    @Override
    public void delete(long id) {
        Event event = eventRepository.getReferenceById(id);
        event.removeEventFromComedians();
        eventRepository.delete(event);
    }

    @Override
    public EventDTO attendedEventByUser(long userId, long eventId, Boolean isAttended) {
        Event event = eventRepository.getReferenceById(eventId);
        if (Boolean.TRUE.equals(isAttended)) {
            userService.addEventToUser(userId, event);
        } else {
            userService.removeEventFromUser(userId, event);
        }
        return eventMapper.toDto(event);
    }

    @Override
    public List<EventDTO> findByTrending() {
        return eventRepository
                .findTop4ByIsTrendingIsTrueAndDateIsAfterOrderByDateAsc(OffsetDateTime.now())
                .stream()
                .map(eventMapper::toDto)
                .toList();
    }

    private void setLocation(Long locationId, EventDTO eventDto) {
        if (locationId != null) {
            LocationDTO locationDTO = locationService.getById(locationId);
            eventDto.setLocation(locationDTO);
        }
    }

    private void setStandup(Long standupId, EventDTO eventDto) {
        if (standupId != null) {
            StandupDTO standupDTO = standupService.getById(standupId);
            eventDto.setStandup(standupDTO);
        }
    }

    private void setComedians(Set<Long> comedianIds, Event event) {
        if (comedianIds != null) {
            for (Long comedianId : comedianIds) {
                Comedian comedian = comedianService.getEntity(comedianId);
                event.addComedian(comedian);
            }
        }
    }
}
