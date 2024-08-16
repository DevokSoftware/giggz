package com.devok.giggz.service.impl;

import org.springframework.stereotype.Service;

import com.devok.giggz.service.StandupService;
import com.devok.giggz.service.dto.StandupDTO;
import com.devok.giggz.service.mapper.StandupMapper;
import com.devok.giggz.service.repository.StandupRepository;

@Service
public class StandupServiceImpl implements StandupService {

    private final StandupRepository standupRepository;
    private final StandupMapper standupMapper;

    public StandupServiceImpl(StandupRepository standupRepository, StandupMapper standupMapper) {
        this.standupRepository = standupRepository;
        this.standupMapper = standupMapper;
    }

    @Override
    public StandupDTO getById(long id) {
        return standupMapper.toDto(standupRepository.getReferenceById(id));
    }
}
