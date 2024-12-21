package com.devok.giggz.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.devok.giggz.service.UserService;
import com.devok.giggz.service.auth.UserPrincipal;
import com.devok.giggz.service.dto.ComedianDTO;
import com.devok.giggz.service.filters.ComediansFilter;
import com.devok.giggz.service.mapper.ComedianMapper;
import com.devok.giggz.service.ComedianService;
import com.devok.giggz.service.model.Comedian;
import com.devok.giggz.service.repository.ComedianRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ComedianServiceImpl implements ComedianService {
    private final ComedianRepository comedianRepository;
    private final ComedianMapper comedianMapper;
    private final UserService userService;

    public ComedianServiceImpl(ComedianRepository comedianRepository, ComedianMapper comedianMapper, UserService userService) {
        this.comedianRepository = comedianRepository;
        this.comedianMapper = comedianMapper;
        this.userService = userService;
    }

    @Override
    public Page<ComedianDTO> findAll(Pageable pageable, ComediansFilter comediansFilter) {
        Page<ComedianDTO> comedians = comedianRepository.findByFilters(comediansFilter.getName(), pageable).map(comedianMapper::toDto);
        return comedians;
    }

    @Override
    public ComedianDTO getById(long id) {
        ComedianDTO comedianDTO = comedianMapper.toDto(comedianRepository.getReferenceById(id));
        fillComedianInfo(comedianDTO);
        return comedianDTO;
    }

    @Override
    public Comedian getEntity(long id) {
        Optional<Comedian> comedian = comedianRepository.findById(id);
        if (comedian.isPresent()) {
            return comedian.get();
        }
        //TODO user custom exception
        throw new EntityNotFoundException();
    }

    @Override
    public ComedianDTO create(ComedianDTO comedian) {
        Comedian createdComedian = comedianRepository.save(comedianMapper.toEntity(comedian));
        return comedianMapper.toDto(createdComedian);
    }

    @Override
    public ComedianDTO update(long id, ComedianDTO updateComedian) {
        Comedian comedian = getEntity(id);
        comedianMapper.updateComedianFromDto(updateComedian, comedian);
        Comedian updatedComedian = comedianRepository.save(comedian);
        return comedianMapper.toDto(updatedComedian);
    }

    @Override
    public void delete(long id) {
        comedianRepository.delete(comedianRepository.getReferenceById(id));
    }

    @Override
    public ComedianDTO favoriteComedianByUser(long userId, long comedianId, Boolean isFavorite) {
        Comedian comedian = comedianRepository.getReferenceById(comedianId);
        if (Boolean.TRUE.equals(isFavorite)) {
            userService.addFavoriteComedianToUser(userId, comedian);
        } else {
            userService.removeFavoriteComedianFromUser(userId, comedian);
        }
        return comedianMapper.toDto(comedian);
    }

    @Override
    public List<ComedianDTO> findFavoriteByUser(long userId) {
        return comedianRepository.findFavoriteComediansByUserId(userId)
                .stream()
                .map(comedianMapper::toDto)
                .toList();
    }

    private void fillComedianInfo(ComedianDTO comedianDTO) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserPrincipal user) {
            List<ComedianDTO> favoriteComedians = findFavoriteByUser(user.getId());
            comedianDTO.setFavoriteOfLoggedUser(favoriteComedians
                    .stream()
                    .anyMatch(comedian -> comedian.getId().equals(comedianDTO.getId())));
        }

    }
}
