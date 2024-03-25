package com.devok.giggz.restapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import com.devok.giggz.openapi.api.LoginApi;
import com.devok.giggz.openapi.model.JwtToken;
import com.devok.giggz.openapi.model.LoginRequest;
import com.devok.giggz.service.auth.JwtService;

@RestController
public class AuthController implements LoginApi {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public ResponseEntity<JwtToken> loginPost(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            JwtToken jwtToken = new JwtToken();
            jwtToken.setAccessToken(jwtService.generateToken(loginRequest.getUsername()));
            return ResponseEntity.ok().body(jwtToken);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
