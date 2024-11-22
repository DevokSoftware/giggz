package com.devok.giggz.restapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.devok.giggz.openapi.model.Standup;
import com.devok.giggz.openapi.model.StandupInput;
import com.devok.giggz.openapi.model.UserProfile;
import com.devok.giggz.service.dto.StandupDTO;
import com.devok.giggz.service.dto.UserDTO;

@Mapper(componentModel = "spring")
public interface UserApiMapper {
    UserProfile toApi(UserDTO userDTO);
}
