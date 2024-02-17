package com.devok.giggz.service.model;

import java.util.Set;

import com.devok.giggz.service.enums.ContentType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comedian_content")
public class ComedianContent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="contenttype")
    @Enumerated(EnumType.STRING)
    private ContentType contentType;
    private String name;
    private String url;
    @ManyToMany(mappedBy = "contents")
    Set<Comedian> comedians;
}


