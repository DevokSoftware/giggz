package com.devok.giggz.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devok.giggz.service.dto.event.CreateEventDTO;
import com.devok.giggz.service.dto.event.EventDTO;
import com.devok.giggz.service.dto.event.UpdateEventDTO;
import com.devok.giggz.service.filters.EventsFilter;

public interface EventService {
    Page<EventDTO> findAll(Pageable pageable, EventsFilter eventsFilter);

    Page<EventDTO> findAllByComedian(Pageable pageable, Long comedianId, OffsetDateTime dateFrom, OffsetDateTime dateTo);

    // TODO implement pageable
    List<EventDTO> findAllByUser(long userId);

    EventDTO attendedEventByUser(long userId, long eventId, Boolean isAttended);

    EventDTO getById(long id);

    EventDTO create(CreateEventDTO event);

    EventDTO update(long id, UpdateEventDTO event);

    void delete(long id);
}
