package com.devok.giggz.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devok.giggz.service.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    @Query(value = "SELECT DISTINCT l FROM Location l " +
            "WHERE (:name IS NULL OR l.name = :name) " +
            "AND (:city IS NULL OR l.city = :city)")
    List<Location> findAllByFilters(
            @Param("name") String name,
            @Param("city") String city);
}