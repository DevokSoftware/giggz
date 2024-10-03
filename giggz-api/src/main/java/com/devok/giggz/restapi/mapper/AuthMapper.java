package com.devok.giggz.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.devok.giggz.openapi.model.SignupRequest;
import com.devok.giggz.service.dto.UserDTO;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    @Mapping(target = "password", source = "hashedPassword" )
    UserDTO toUserDto(SignupRequest signupRequest, String hashedPassword);
}
