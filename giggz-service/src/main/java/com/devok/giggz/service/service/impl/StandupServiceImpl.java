package com.devok.giggz.service.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devok.giggz.service.service.ComedianService;
import com.devok.giggz.service.service.StandupService;
import com.devok.giggz.service.dto.ComedianDTO;
import com.devok.giggz.service.dto.standup.StandupDTO;
import com.devok.giggz.service.dto.standup.StandupInputDTO;
import com.devok.giggz.service.mapper.StandupMapper;
import com.devok.giggz.service.model.Standup;
import com.devok.giggz.service.repository.StandupRepository;

@Service
public class StandupServiceImpl implements StandupService {

    private final StandupRepository standupRepository;
    private final StandupMapper standupMapper;
    private final ComedianService comedianService;

    public StandupServiceImpl(StandupRepository standupRepository,
                              StandupMapper standupMapper,
                              ComedianService comedianService) {
        this.standupRepository = standupRepository;
        this.standupMapper = standupMapper;
        this.comedianService = comedianService;
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
    public StandupDTO create(StandupInputDTO standupInputDTO) {
        StandupDTO standupDTO = standupMapper.toDto(standupInputDTO);
        setComedian(standupInputDTO.getComedianId(), standupDTO);
        Standup standup = standupRepository.save(standupMapper.toEntity(standupDTO));
        return standupMapper.toDto(standup);
    }

    @Override
    public StandupDTO update(long id, StandupInputDTO standupInputDTO) {
        StandupDTO standupDTO = getById(id);
        standupMapper.updateValues(standupInputDTO, standupDTO);
        setComedian(standupInputDTO.getComedianId(), standupDTO);
        Standup updatedStandup = standupRepository.save(standupMapper.toEntity(standupDTO));
        return standupMapper.toDto(updatedStandup);
    }

    @Override
    public void delete(long id) {
        standupRepository.delete(standupRepository.getReferenceById(id));
    }

    @Override
    public List<StandupDTO> getLastStandupUpdates() {
        return standupMapper.toDtoList(standupRepository.findStandupsByIsTrendingIsTrueOrderByCreatedAtDesc());
    }

    private void setComedian(Long comedianId, StandupDTO standupDto) {
        if (comedianId != null) {
            ComedianDTO comedianDTO = comedianService.getById(comedianId);
            standupDto.setComedian(comedianDTO);
        }
    }
}
