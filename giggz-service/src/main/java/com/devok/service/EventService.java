package com.devok.service;


import java.util.List;

import com.devok.service.dto.EventDTO;

public interface EventService {
    List<EventDTO> findAll();
    EventDTO create(EventDTO event);
    EventDTO update(EventDTO event);
}
