package com.devok.giggz.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.security.core.context.SecurityContextHolder;

import com.devok.giggz.service.auth.UserPrincipal;
import com.devok.giggz.service.dto.ComedianDTO;
import com.devok.giggz.service.model.Comedian;

@Mapper(componentModel = "spring")
public interface ComedianMapper {
    @Mapping(target = "favoriteOfLoggedUser", expression = "java(setFavoriteOfLoggedUser(comedian))")
    ComedianDTO toDto(Comedian comedian);

    Comedian toEntity(ComedianDTO comedian);

    @Mapping(target = "events", ignore = true)
        // Ignore the events field
    void updateComedianFromDto(ComedianDTO dto, @MappingTarget Comedian entity);

    default boolean setFavoriteOfLoggedUser(Comedian comedian) {
        if (comedian.getUsers() == null) {
            return false;
        }
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserPrincipal user
                && !comedian.getUsers().isEmpty() && comedian.getUsers().stream().anyMatch(u -> u.getId() == user.getId());
    }
}
