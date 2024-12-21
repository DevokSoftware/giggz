package com.devok.giggz.restapi.mapper;

import org.mapstruct.Mapper;

import com.devok.giggz.openapi.model.UserProfile;
import com.devok.giggz.service.dto.UserDTO;

@Mapper(componentModel = "spring")
public interface UserApiMapper {
    UserProfile toApi(UserDTO userDTO);
}
