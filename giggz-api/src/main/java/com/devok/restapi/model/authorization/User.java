package com.devok.restapi.model.authorization;


import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class User implements UserDetails {

    @Id
    private long id;

    @OneToMany
    private Set<Authority> authorities;

    private String password;

    private String name;

    @Column(unique = true)
    private String username;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private Provider provider;

    private boolean enabled;
}