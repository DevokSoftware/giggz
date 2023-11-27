package com.devok.giggz.service;


import java.util.List;

import com.devok.giggz.dto.EventDTO;

public interface EventService {
    List<EventDTO> findAll();
}
