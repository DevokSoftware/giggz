package com.devok.giggz.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devok.giggz.service.dto.ComedianDTO;
import com.devok.giggz.service.filters.ComediansFilter;

public interface ComedianService {
    Page<ComedianDTO> findAll(Pageable pageable, ComediansFilter comediansFilter);
    ComedianDTO getById(long id);
    ComedianDTO create(ComedianDTO comedian);
    ComedianDTO update(long id, ComedianDTO comedian);
    void delete(long id);
}
