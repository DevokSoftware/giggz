package com.devok.giggz.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.security.core.context.SecurityContextHolder;

import com.devok.giggz.service.auth.UserPrincipal;
import com.devok.giggz.service.dto.event.CreateEventDTO;
import com.devok.giggz.service.dto.event.EventDTO;
import com.devok.giggz.service.dto.event.UpdateEventDTO;
import com.devok.giggz.service.model.Event;

@Mapper(componentModel = "spring")
public interface EventMapper {
    @Mapping(target = "isAttendedByLoggedUser", expression = "java(setIsAttendedByLoggedUser(event))")
    EventDTO toDto(Event event);

    Event toEntity(EventDTO event);

    EventDTO toEntity(CreateEventDTO event);

    @Mapping(target = "id", ignore = true)
    void updateValues(UpdateEventDTO updatedEvent, @MappingTarget EventDTO eventDto);

    default Boolean setIsAttendedByLoggedUser(Event event) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserPrincipal user) {
            if (event.getUsers().stream().anyMatch(u -> u.getId() == user.getId())) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
