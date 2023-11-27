package com.devok.giggz.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.devok.giggz.dto.EventDTO;
import com.devok.giggz.model.Event;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDTO eventToEventDTO(Event event);
    List<EventDTO> eventsToEventDTOs(List<Event> event);
}
