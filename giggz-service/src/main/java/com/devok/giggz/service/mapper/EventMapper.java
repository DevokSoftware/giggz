package com.devok.giggz.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.devok.giggz.service.dto.event.CreateEventDTO;
import com.devok.giggz.service.dto.event.EventDTO;
import com.devok.giggz.service.dto.event.UpdateEventDTO;
import com.devok.giggz.service.model.Event;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDTO toDto(Event event);

    List<EventDTO> toDtoList(List<Event> event);

    Event toEntity(EventDTO event);

    EventDTO toEntity(CreateEventDTO event);

    @Mapping(target = "id", ignore = true)
    void updateValues(UpdateEventDTO updatedEvent, @MappingTarget EventDTO eventDto);
}
