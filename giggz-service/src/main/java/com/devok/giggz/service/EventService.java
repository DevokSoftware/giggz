package com.devok.giggz.service;

import java.time.OffsetDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devok.giggz.service.dto.EventDTO;
import com.devok.giggz.service.filters.EventsFilter;

public interface EventService {
    Page<EventDTO> findAll(Pageable pageable, EventsFilter eventsFilter);

    Page<EventDTO> findAllByComedian(Pageable pageable, Long comedianId, OffsetDateTime dateFrom, OffsetDateTime dateTo);

    EventDTO getById(long id);

    EventDTO create(EventDTO event);

    EventDTO update(long id, EventDTO event);

    void delete(long id);
}
