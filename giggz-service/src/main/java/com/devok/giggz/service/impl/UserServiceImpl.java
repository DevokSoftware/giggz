package com.devok.giggz.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devok.giggz.service.auth.CustomUserDetails;
import com.devok.giggz.service.model.authorization.UserInfo;
import com.devok.giggz.service.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Could not found user..!!");
        }
        return new CustomUserDetails(user);
    }
}
