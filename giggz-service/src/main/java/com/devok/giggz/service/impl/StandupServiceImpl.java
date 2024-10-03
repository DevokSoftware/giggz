package com.devok.giggz.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devok.giggz.service.StandupService;
import com.devok.giggz.service.dto.StandupDTO;
import com.devok.giggz.service.mapper.StandupMapper;
import com.devok.giggz.service.model.Standup;
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

    @Override
    public List<StandupDTO> findAll() {
        return standupMapper.toDtoList(standupRepository.findAll());
    }



    @Override
    public StandupDTO create(StandupDTO standupDTO) {
        Standup standup = standupRepository.save(standupMapper.toEntity(standupDTO));
        return standupMapper.toDto(standup);
    }

    @Override
    public StandupDTO update(long id, StandupDTO updateStandup) {
        StandupDTO standup = getById(id);
        standupMapper.updateValues(updateStandup, standup);
        Standup updatedStandup = standupRepository.save(standupMapper.toEntity(standup));
        return standupMapper.toDto(updatedStandup);
    }

    @Override
    public void delete(long id) {
        standupRepository.delete(standupRepository.getReferenceById(id));
    }
}
