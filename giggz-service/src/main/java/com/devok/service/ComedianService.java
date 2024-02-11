package com.devok.service;

import java.util.List;

import com.devok.service.dto.ComedianDTO;

public interface ComedianService {
    List<ComedianDTO> findAll();
    ComedianDTO getById(long id);
    ComedianDTO create(ComedianDTO comedian);
    ComedianDTO update(long id, ComedianDTO comedian);
}
