package com.devok.restapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.devok.restapi.model.CreateEventRequest;
import com.devok.restapi.model.EventResponse;
import com.devok.service.dto.EventDTO;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDTO toDTO (CreateEventRequest createEventRequest);
    EventResponse toEventResponse(EventDTO event);
    List<EventResponse> toEventResponseList(List<EventDTO> event);
}
