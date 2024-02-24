package com.devok.giggz.restapi.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import com.devok.giggz.openapi.model.CreateEventRequest;
import com.devok.giggz.openapi.model.EventResponse;
import com.devok.giggz.openapi.model.EventsGetFiltersParameter;
import com.devok.giggz.openapi.model.PageEventResponse;
import com.devok.giggz.openapi.model.UpdateEventRequest;
import com.devok.giggz.service.dto.EventDTO;
import com.devok.giggz.service.filters.EventsFilter;

@Mapper(componentModel = "spring")
public interface EventApiMapper {
    EventDTO toDTO (CreateEventRequest createEventRequest);
    EventDTO toDTO (UpdateEventRequest createEventRequest);
    EventResponse toEventResponse(EventDTO event);
    EventsFilter toEventsFilter(EventsGetFiltersParameter eventsGetFiltersParameter);
    PageEventResponse toPageEventResponse(Page<EventDTO> eventsPage);
}
