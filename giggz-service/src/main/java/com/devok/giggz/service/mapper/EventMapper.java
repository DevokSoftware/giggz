package com.devok.giggz.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.devok.giggz.service.dto.EventDTO;
import com.devok.giggz.service.model.Event;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDTO toDto(Event event);

    List<EventDTO> toDtoList(List<Event> event);

    Event toEntity(EventDTO event);

    void updateValues(EventDTO updatedEvent, @MappingTarget EventDTO eventDto);
}
