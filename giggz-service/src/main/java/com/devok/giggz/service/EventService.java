package com.devok.giggz.service;


import java.util.List;

import com.devok.giggz.service.dto.EventDTO;

public interface EventService {
    List<EventDTO> findAll();
    EventDTO getById(long id);
    EventDTO create(EventDTO event);
    EventDTO update(long id, EventDTO event);
}
