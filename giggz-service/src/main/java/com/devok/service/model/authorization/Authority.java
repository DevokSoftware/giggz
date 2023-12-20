package com.devok.service.model.authorization;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@Entity
public class Authority implements GrantedAuthority {

    @Id
    private long id;

    public String authority;
}