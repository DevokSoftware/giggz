package com.devok.giggz.restapi.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.bind.annotation.RestController;

import com.devok.giggz.auth.jwt.TokenProvider;
import com.devok.giggz.openapi.api.AuthApi;
import com.devok.giggz.openapi.model.AuthSignupPost200Response;
import com.devok.giggz.openapi.model.JwtToken;
import com.devok.giggz.openapi.model.LoginRequest;
import com.devok.giggz.openapi.model.SignupRequest;
import com.devok.giggz.openapi.model.TokenRefreshRequest;
import com.devok.giggz.openapi.model.ValidateToken200Response;
import com.devok.giggz.restapi.mapper.AuthMapper;
import com.devok.giggz.service.UserService;
import com.devok.giggz.service.dto.UserDTO;

@RestController
public class AuthController implements AuthApi {
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final UserService userService;
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, TokenProvider tokenProvider,
                          UserService userService, AuthMapper authMapper, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
        this.authMapper = authMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<JwtToken> authLoginPost(LoginRequest loginRequest) {
        Optional<UserDTO> userOptional = userService.findByEmail(loginRequest.getEmail());

        // Check if the user exists
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            // Authenticate the user by checking the email and password
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            if (authentication.isAuthenticated()) {
                // Generate JWT token upon successful authentication
                JwtToken jwtToken = new JwtToken();
                jwtToken.setAccessToken(tokenProvider.generateAccessToken(loginRequest.getEmail()));
                jwtToken.setRefreshToken(tokenProvider.generateRefreshToken(loginRequest.getEmail()));
                return ResponseEntity.ok(jwtToken);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    public ResponseEntity<AuthSignupPost200Response> authSignupPost(SignupRequest signupRequest) {
        Optional<UserDTO> user = userService.findByEmail(signupRequest.getEmail());
        if (user.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new AuthSignupPost200Response().message("Email is already in use."));
        }

        try {
            String hashedPassword = passwordEncoder.encode(signupRequest.getPassword());
            userService.createUser(authMapper.toUserDto(signupRequest, hashedPassword));
            return ResponseEntity.ok(new AuthSignupPost200Response().message("User registered successfully!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AuthSignupPost200Response().message("An error occurred during registration."));
        }
    }

    @Override
    public ResponseEntity<ValidateToken200Response> validateToken(String authorization) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ValidateToken200Response().message("Missing or invalid Authorization header"));
        }
        String token = authorization.substring(7); // Remove "Bearer " from the token
        try {
            tokenProvider.validateToken(token);
            return ResponseEntity.ok(new ValidateToken200Response().message("Token is valid"));
        } catch (JwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ValidateToken200Response().message("Invalid token: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ValidateToken200Response().message("Token validation failed"));
        }
    }

    @Override
    public ResponseEntity<JwtToken> authRefreshPost(TokenRefreshRequest tokenRefreshRequest) {
        JwtToken jwtToken = new JwtToken();
        try {
            String refreshToken = tokenRefreshRequest.getRefreshToken();
            String accessToken = tokenProvider.generateAccessTokenFromRefreshToken(refreshToken);
            jwtToken.setAccessToken(accessToken);
            jwtToken.setRefreshToken(refreshToken);
            return ResponseEntity.ok(jwtToken);
        } catch (JwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
