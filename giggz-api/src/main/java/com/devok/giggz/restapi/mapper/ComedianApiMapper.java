package com.devok.giggz.restapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import com.devok.giggz.openapi.model.ComedianResponse;
import com.devok.giggz.openapi.model.ComediansGetFiltersParameter;
import com.devok.giggz.openapi.model.PageComedianResponse;
import com.devok.giggz.service.dto.ComedianDTO;
import com.devok.giggz.service.filters.ComediansFilter;

@Mapper(componentModel = "spring")
public interface ComedianApiMapper {
    ComedianResponse toComedianResponse(ComedianDTO comedian);
    List<ComedianResponse> toComedianResponseList(List<ComedianDTO> comedian);
    ComediansFilter toComediansFilter(ComediansGetFiltersParameter comediansGetFiltersParameter);
    PageComedianResponse toPageComedianResponse(Page<ComedianDTO> comediansPage);
}
