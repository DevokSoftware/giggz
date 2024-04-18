package com.devok.giggz.service.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.hibernate.Hibernate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OrderBy;
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
    @JoinTable(name = "comedians_contents", joinColumns = @JoinColumn(name = "comedian_id"), inverseJoinColumns = @JoinColumn(name = "content_id") )
    @OrderBy("contentType")
    private Set<ComedianContent> contents;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "comedians_events", joinColumns = @JoinColumn(name = "comedian_id"), inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<Event> events;

    public void addEvent(Event event) {
        if(events == null){
            events = new HashSet<>();
        }
        event.getComedians().add(this);
    }
    public void removeEvent(Event event) {
        if(events == null){
            return;
        }
        this.events.remove(event);
        event.getComedians().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {return false;}
        Comedian comedian = (Comedian) o;
        return Objects.equals(id, comedian.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}


