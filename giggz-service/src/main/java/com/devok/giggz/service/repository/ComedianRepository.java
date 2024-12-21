package com.devok.giggz.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devok.giggz.service.model.Comedian;

@Repository
public interface ComedianRepository extends JpaRepository<Comedian, Long> {
    @Query(value = "SELECT c FROM Comedian c " +
            "WHERE (:comedianName IS NULL or :comedianName = '' OR (LOWER(c.name) LIKE LOWER(CONCAT('%', :comedianName, '%'))))" +
            "AND c.inactive = false " +
            "ORDER BY c.priority asc")
    Page<Comedian> findByFilters(
            @Param("comedianName") String comedianName,
            Pageable pageable);

    @Query("SELECT c FROM Comedian c " +
            "JOIN c.users u " +
            "WHERE u.id = :userId")
    List<Comedian> findFavoriteComediansByUserId(@Param("userId") long userId);
}
