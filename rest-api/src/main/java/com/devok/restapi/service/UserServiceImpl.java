package com.devok.restapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devok.restapi.model.authorization.Provider;
import com.devok.restapi.model.authorization.User;
import com.devok.restapi.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
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
