package com.devok.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devok.service.model.Comedian;

@Repository
public interface ComedianRepository extends JpaRepository<Comedian, Long> {

}
