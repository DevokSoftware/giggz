package com.devok.giggz.service;

import java.time.OffsetDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devok.giggz.service.dto.event.CreateEventDTO;
import com.devok.giggz.service.dto.event.EventDTO;
import com.devok.giggz.service.dto.event.UpdateEventDTO;
import com.devok.giggz.service.filters.EventsFilter;

public interface EventService {
    Page<EventDTO> findAll(Pageable pageable, EventsFilter eventsFilter);

    Page<EventDTO> findAllByComedian(Pageable pageable, Long comedianId, OffsetDateTime dateFrom, OffsetDateTime dateTo);

    EventDTO getById(long id);

    EventDTO create(CreateEventDTO event);

    EventDTO update(long id, UpdateEventDTO event);

    void delete(long id);
}
