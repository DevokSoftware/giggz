package com.devok.giggz.service.mapper;

import org.mapstruct.Mapper;

import com.devok.giggz.service.dto.UserDTO;
import com.devok.giggz.service.model.authorization.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User user);

    User toEntity(UserDTO user);
}
