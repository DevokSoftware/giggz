package com.devok.giggz.service.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Iterator;
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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private OffsetDateTime date;
    private String description;
    private String poster;
    private BigDecimal price;
    private Integer priority;
    private String url;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "location")
    private Location location;
    @ManyToMany(mappedBy = "events", fetch = FetchType.LAZY)
    Set<Comedian> comedians;

    public void addComedian(Comedian comedian) {
        if (comedians == null) {
            comedians = new HashSet<>();
        }
        comedians.add(comedian);
        comedian.getEvents().add(this);
    }

    public void removeEventFromComedians() {
        if (comedians == null) {
            return;
        }
        Iterator<Comedian> iterator = comedians.iterator();
        while (iterator.hasNext()) {
            Comedian comedian = iterator.next();
            if (!comedian.getEvents().isEmpty()) {
                comedian.getEvents().removeIf(event -> event.equals(this));
            }
            iterator.remove();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Event event = (Event) o;
        return Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
