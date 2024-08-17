package com.devok.giggz.service;

import java.util.List;

import com.devok.giggz.service.dto.StandupDTO;

public interface StandupService {
    StandupDTO getById(long id);
    List<StandupDTO> findAll();
    StandupDTO create(StandupDTO standupDTO);
    StandupDTO update(long id, StandupDTO standupDTO);
    void delete(long id);
}
