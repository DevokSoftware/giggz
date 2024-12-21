package com.devok.giggz.service.dto;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.devok.giggz.service.dto.event.EventDTO;
import com.devok.giggz.service.enums.AuthProvider;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    @Id
    private long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String imageUrl;
    private Boolean emailVerified = false;
    @JsonIgnore
    private String password = null;
    private AuthProvider provider;
    private String providerId;
    private List<RoleDTO> roles;
    private List<EventDTO> attendedEvents;
    private List<ComedianDTO> favoriteComedians;
}
