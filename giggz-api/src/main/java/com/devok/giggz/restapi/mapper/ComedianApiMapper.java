package com.devok.giggz.restapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.devok.giggz.openapi.model.ComedianResponse;
import com.devok.giggz.service.dto.ComedianDTO;

@Mapper(componentModel = "spring")
public interface ComedianApiMapper {
    ComedianResponse toComedianResponse(ComedianDTO comedian);
    List<ComedianResponse> toComedianResponseList(List<ComedianDTO> comedian);
}
