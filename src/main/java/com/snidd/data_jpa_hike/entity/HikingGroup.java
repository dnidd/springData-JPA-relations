package com.snidd.data_jpa_hike.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "HikingGroup")
@Table(name = "hiking_group")
public class HikingGroup {

    // PK
    @Id
    // Set the name of id sequence.
    @SequenceGenerator(
            name = "hiking_group_sequence",
            // The name from where to fetch PK values.
            sequenceName = "hiking_group_sequence",
            // Increment id by 1 (allocated in memory).
            allocationSize = 1
    )
    @GeneratedValue(
            // .SEQUENCE: generates incremented PK value id.
            strategy = GenerationType.SEQUENCE,
            // link GeneratedValue to custom SequenceGenerator
            generator = "hiking_group_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;


    @Column(
            name = "name",
            nullable = false
    )
    private String name;


    @ManyToMany(
            mappedBy = "hikingGroups"
    )
    private Set<Hiker> hikers = new HashSet<>();


    public HikingGroup() {}

    public HikingGroup(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Hiker> getHikers() {
        return hikers;
    }

    public void setHikers(Set<Hiker> hikers) {
        this.hikers = hikers;
    }

    @Override
    public String toString() {
        return "HikingGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
