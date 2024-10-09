package com.devok.giggz.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.devok.giggz.service.dto.UserDTO;
import com.devok.giggz.service.model.Event;

public interface UserService extends UserDetailsService {
    Optional<UserDTO> findByEmail(String email);
    UserDTO findById(long userId);
    void addEventToUser(long userId, Event event);
    void removeEventFromUser(long userId, Event event);
    UserDTO upsertOAuthUser(UserDTO userDTO);
    UserDTO createUser(UserDTO userDTO);
}
