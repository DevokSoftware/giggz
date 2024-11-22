package com.devok.giggz.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devok.giggz.service.UserService;
import com.devok.giggz.service.auth.UserPrincipal;
import com.devok.giggz.service.dto.UserDTO;
import com.devok.giggz.service.mapper.UserMapper;
import com.devok.giggz.service.model.Event;
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
        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .toList();

        return new UserPrincipal(user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                authorities);
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
    public UserDTO findById(long userId) {
        return userMapper.toDto(userRepository.getReferenceById(userId));
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

    @Override
    public void addEventToUser(long userId, Event event) {
        User user = userRepository.getReferenceById(userId);
        if (!user.getAttendedEvents().contains(event)) {
            user.getAttendedEvents().add(event);
            userRepository.save(user);
        }
    }

    @Override
    public void removeEventFromUser(long userId, Event event) {
         User user = userRepository.getReferenceById(userId);
        if (user.getAttendedEvents().contains(event)) {
            user.getAttendedEvents().remove(event);
            userRepository.save(user);
        }
    }

    private UserDTO save(UserDTO userDTO) {
        User user = userRepository.save(userMapper.toEntity(userDTO));
        return userMapper.toDto(user);
    }
}
