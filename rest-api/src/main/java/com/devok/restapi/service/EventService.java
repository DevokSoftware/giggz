package com.devok.restapi.service;


import java.util.List;

import com.devok.restapi.dto.EventDTO;

public interface EventService {
    List<EventDTO> findAll();
}
