package com.devok.giggz.service.service;

import java.util.List;

import com.devok.giggz.service.dto.standup.StandupDTO;
import com.devok.giggz.service.dto.standup.StandupInputDTO;

public interface StandupService {
    StandupDTO getById(long id);
    List<StandupDTO> findAll();
    StandupDTO create(StandupInputDTO standupDTO);
    StandupDTO update(long id, StandupInputDTO standupDTO);
    void delete(long id);
    List<StandupDTO> getLastStandupUpdates();
}
