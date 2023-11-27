package com.devok.giggz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devok.giggz.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
