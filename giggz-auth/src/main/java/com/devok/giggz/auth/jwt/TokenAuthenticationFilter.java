package com.devok.giggz.auth.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.devok.giggz.service.service.UserService;
import com.devok.giggz.service.auth.UserPrincipal;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    private final UserService userService;

    public TokenAuthenticationFilter(TokenProvider tokenProvider, UserService userService) {
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String email;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            //TODO check this response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authHeader.substring(7);
        try {
            email = tokenProvider.extractEmail(token);
        } catch (ExpiredJwtException e) {
            logger.warn("JWT token has expired.");
            // Optionally, you could set a response status here
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return; // Stop further processing
        } catch (Exception e) {
            logger.error("An error occurred while extracting JWT token", e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        //TODO check if it is needed to check everytime if the user is valid in DB
//        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        if (email != null) {
            UserPrincipal userPrincipal = (UserPrincipal) userService.loadUserByUsername(email);
            if (Boolean.TRUE.equals(tokenProvider.validateTokenForUser(token, userPrincipal))) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}