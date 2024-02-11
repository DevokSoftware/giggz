package com.devok.restapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.devok.restapi.model.ComedianResponse;
import com.devok.service.dto.ComedianDTO;

@Mapper(componentModel = "spring")
public interface ComedianMapper {
    ComedianResponse toComedianResponse(ComedianDTO comedian);
    List<ComedianResponse> toComedianResponseList(List<ComedianDTO> comedian);
}
