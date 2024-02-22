package com.devok.giggz.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devok.giggz.service.dto.EventDTO;
import com.devok.giggz.service.filters.EventsFilter;

public interface EventService {
    Page<EventDTO> findAll(Pageable pageable, EventsFilter eventsFilter);
    EventDTO getById(long id);
    EventDTO create(EventDTO event);
    EventDTO update(long id, EventDTO event);
}
