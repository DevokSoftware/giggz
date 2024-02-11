package com.devok.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devok.service.ComedianService;
import com.devok.service.dto.ComedianDTO;
import com.devok.service.mapper.ComedianMapper;
import com.devok.service.model.Comedian;
import com.devok.service.repository.ComedianRepository;

@Service
public class ComedianServiceImpl implements ComedianService {
    private final ComedianRepository comedianRepository;
    private final ComedianMapper comedianMapper;

    public ComedianServiceImpl(ComedianRepository comedianRepository, ComedianMapper comedianMapper) {
        this.comedianRepository = comedianRepository;
        this.comedianMapper = comedianMapper;
    }

    @Override
    public List<ComedianDTO> findAll() {
        return comedianMapper.toDtoList(comedianRepository.findAll());
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
    public ComedianDTO update(long id, ComedianDTO comedian) {
        return null;
    }
}
