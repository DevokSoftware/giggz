package com.devok.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devok.service.model.authorization.Provider;
import com.devok.service.model.authorization.User;
import com.devok.service.repository.UserRepository;
import com.devok.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void processOAuthPostLogin(String username) {
        Optional<User> existUser = userRepository.findByUsername(username);
        if (existUser.isEmpty()) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setProvider(Provider.GOOGLE);
            newUser.setEnabled(true);
            userRepository.save(newUser);
        }

    }
}
