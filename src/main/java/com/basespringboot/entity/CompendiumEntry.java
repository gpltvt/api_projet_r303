package com.basespringboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Getter 
@Setter
public class CompendiumEntry {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Integer apiId;

    private String name;
    private String category;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String image;
    private Boolean dlc;

    @Column(columnDefinition = "TEXT")
    private String commonLocations;
}
