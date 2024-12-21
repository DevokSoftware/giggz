package com.devok.giggz.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devok.giggz.service.dto.ComedianDTO;
import com.devok.giggz.service.filters.ComediansFilter;
import com.devok.giggz.service.model.Comedian;

public interface ComedianService {
    Page<ComedianDTO> findAll(Pageable pageable, ComediansFilter comediansFilter);
    ComedianDTO getById(long id);
    Comedian getEntity(long id);
    ComedianDTO create(ComedianDTO comedian);
    ComedianDTO update(long id, ComedianDTO comedian);
    void delete(long id);
    ComedianDTO favoriteComedianByUser(long userId, long comedianId, Boolean isAttended);
    List<ComedianDTO> findFavoriteByUser(long userId);
}
