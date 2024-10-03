package com.devok.giggz.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.devok.giggz.service.dto.UserDTO;

public interface UserService extends UserDetailsService {
    Optional<UserDTO> findByEmail(String email);
    UserDTO upsertOAuthUser(UserDTO userDTO);
    UserDTO createUser(UserDTO userDTO);
}
