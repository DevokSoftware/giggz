package com.devok.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.devok.service.dto.EventDTO;
import com.devok.service.model.Event;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDTO toDto(Event event);
    List<EventDTO> toDtoList(List<Event> event);
    Event toEntity(EventDTO event);
}