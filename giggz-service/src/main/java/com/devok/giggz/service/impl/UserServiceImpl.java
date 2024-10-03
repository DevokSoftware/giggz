package com.devok.giggz.service.impl;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devok.giggz.service.UserService;
import com.devok.giggz.service.auth.UserPrincipal;
import com.devok.giggz.service.dto.UserDTO;
import com.devok.giggz.service.mapper.UserMapper;
import com.devok.giggz.service.model.authorization.User;
import com.devok.giggz.service.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Could not found user..!!");
        }
        return new UserPrincipal(user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                null);
    }

    @Override
    public Optional<UserDTO> findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return Optional.of(userMapper.toDto(user));
        }
        return Optional.empty();
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        // TODO Validate email format and password strength
        return save(userDTO);
    }
    @Override
    public UserDTO upsertOAuthUser(UserDTO userDTO) {
        return save(userDTO);
    }

    private UserDTO save(UserDTO userDTO) {
        User user = userRepository.save(userMapper.toEntity(userDTO));
        return userMapper.toDto(user);
    }
}
