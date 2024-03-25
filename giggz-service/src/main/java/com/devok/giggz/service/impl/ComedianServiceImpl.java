package com.devok.giggz.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devok.giggz.service.dto.ComedianDTO;
import com.devok.giggz.service.filters.ComediansFilter;
import com.devok.giggz.service.mapper.ComedianMapper;
import com.devok.giggz.service.ComedianService;
import com.devok.giggz.service.model.Comedian;
import com.devok.giggz.service.repository.ComedianRepository;

@Service
public class ComedianServiceImpl implements ComedianService {
    private final ComedianRepository comedianRepository;
    private final ComedianMapper comedianMapper;

    public ComedianServiceImpl(ComedianRepository comedianRepository, ComedianMapper comedianMapper) {
        this.comedianRepository = comedianRepository;
        this.comedianMapper = comedianMapper;
    }

    @Override
    public Page<ComedianDTO> findAll(Pageable pageable, ComediansFilter comediansFilter) {
        return comedianRepository.findByFilters(comediansFilter.getName(), pageable).map(comedianMapper::toDto);
    }

    @Override
    public ComedianDTO getById(long id) {
        return comedianMapper.toDto(comedianRepository.getReferenceById(id));
    }

    @Override
    public ComedianDTO create(ComedianDTO comedian) {
        Comedian createdComedian = comedianRepository.save(comedianMapper.toEntity(comedian));
        return comedianMapper.toDto(createdComedian);
    }

    @Override
    public ComedianDTO update(long id, ComedianDTO updateComedian) {
        ComedianDTO comedian = getById(id);
        comedianMapper.updateValues(updateComedian, comedian);
        Comedian createdComedian = comedianRepository.save(comedianMapper.toEntity(comedian));
        return comedianMapper.toDto(createdComedian);
    }

    @Override
    public void delete(long id) {
        comedianRepository.delete(comedianRepository.getReferenceById(id));
    }
}
