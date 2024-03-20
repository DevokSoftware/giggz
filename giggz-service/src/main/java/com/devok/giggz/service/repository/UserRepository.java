package com.devok.giggz.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devok.giggz.service.model.authorization.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUsername(String username);
    UserInfo findFirstById(Long id);
}