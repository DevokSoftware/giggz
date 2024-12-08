package com.devok.giggz.service.repository;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devok.giggz.service.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query(value = "SELECT DISTINCT e FROM Event e " +
            "LEFT JOIN e.comedians c " +
            "LEFT JOIN e.location l " +
            "LEFT JOIN e.standup s " +
            "WHERE (:eventName IS NULL OR :eventName = '' OR (LOWER(e.name) LIKE LOWER(CONCAT('%', :eventName, '%'))) OR (LOWER(s.name) LIKE LOWER(CONCAT('%', :eventName, '%')))) " +
            "AND (:comedianId IS NULL OR c.id = :comedianId) " +
            "AND (:locationCity IS NULL OR l.city = :locationCity)" +
            "AND (:locationCity IS NULL OR :locationCity = '' OR (LOWER(l.city) LIKE LOWER(CONCAT('%', :locationCity, '%')))) " +
            "AND (cast(:dateFrom as timestamp) IS NULL OR e.date >= :dateFrom)" +
            "AND (cast(:dateTo as timestamp) IS NULL OR e.date <= :dateTo)" +
            "AND (:standupId IS NULL OR s.id = :standupId) ")
    Page<Event> findAllByFilters(
            @Param("eventName") String eventName,
            @Param("comedianId") Long comedianId,
            @Param("locationCity") String locationCity,
            @Param("dateFrom") OffsetDateTime dateFrom,
            @Param("dateTo") OffsetDateTime dateTo,
            @Param("standupId") Long standupId,
            Pageable pageable);

    @Query("SELECT e FROM Event e " +
            "JOIN e.users u " +
            "WHERE u.id = :userId")
    List<Event> findEventsByUserId(@Param("userId") long userId);

    List<Event> findTop4ByIsTrendingIsTrueAndDateIsAfterOrderByDateAsc(OffsetDateTime date);
}
