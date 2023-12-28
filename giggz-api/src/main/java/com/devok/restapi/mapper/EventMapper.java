package com.devok.restapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.devok.restapi.model.CreateEventRequest;
import com.devok.restapi.model.EventResponse;
import com.devok.restapi.model.UpdateEventRequest;
import com.devok.service.dto.EventDTO;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDTO toDTO (CreateEventRequest createEventRequest);
    EventDTO toDTO (UpdateEventRequest createEventRequest);
    EventResponse toEventResponse(EventDTO event);
    List<EventResponse> toEventResponseList(List<EventDTO> event);
}
