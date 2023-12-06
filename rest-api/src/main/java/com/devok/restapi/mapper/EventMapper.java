package com.devok.restapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.devok.restapi.dto.EventDTO;
import com.devok.restapi.model.Event;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDTO map(Event event);
    List<EventDTO> map(List<Event> event);
}
