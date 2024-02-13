package com.devok.giggz.service.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comedian {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private String picture;
    private Integer priority;
    private String instagram;
    private String youtube;
    private String tiktok;
    private String twitter;
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "comedians_contents", joinColumns = @JoinColumn(name = "comedian_id"), inverseJoinColumns = @JoinColumn(name = "content_id"))
    private Set<ComedianContent> contents;
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "comedians_events", joinColumns = @JoinColumn(name = "comedian_id"), inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<Event> events;
}


