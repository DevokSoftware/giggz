package com.devok.giggz.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devok.giggz.service.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
