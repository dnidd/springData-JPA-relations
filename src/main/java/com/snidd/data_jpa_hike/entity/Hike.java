package com.snidd.data_jpa_hike.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "Hike")
@Table(name = "hike")
public class Hike {

    // PK
    @Id
    // Set the name of id sequence.
    @SequenceGenerator(
            name = "hike_sequence",
            // The name from where to fetch PK values.
            sequenceName = "hike_sequence",
            // Increment id by 1 (allocated in memory).
            allocationSize = 1
    )
    @GeneratedValue(
            // .SEQUENCE: generates incremented PK value id.
            strategy = GenerationType.SEQUENCE,
            // link GeneratedValue to custom SequenceGenerator
            generator = "hike_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;


    @Column(
            name = "name"
    )
    private String name;

    @Column(
            name = "date"
    )
    private LocalDate date;

    @Column(
            name = "distanceKm"
    )
    private double distanceKm;


    @ManyToOne()
    @JoinColumn(
            name = "hiker_id", // column name hiker_id holds id's from hikers.
            referencedColumnName = "id", // holds id's from column 'id' in Hiker.
            foreignKey = @ForeignKey(
                    name = "hiker_hike_fk"
            ),
            nullable = false
    )
    private Hiker hiker;


    /**
     * No args constructor.
     */
    public Hike() {}


    /**
     * Constructor.
     * @param name name.
     * @param date date.
     * @param distanceKm distanceKm.
     */
    public Hike(String name, LocalDate date, double distanceKm) {
        this.name = name;
        this.date = date;
        this.distanceKm = distanceKm;
    }


    /**
     * All args constructor.
     * @param name name.
     * @param date date.
     * @param distanceKm distanceKm.
     * @param hiker Hiker.
     */
    public Hike(String name, LocalDate date, double distanceKm, Hiker hiker) {
        this.name = name;
        this.date = date;
        this.distanceKm = distanceKm;
        this.hiker = hiker;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public Hiker getHiker() {
        return hiker;
    }

    public void setHiker(Hiker hiker) {
        this.hiker = hiker;
    }


    @Override
    public String toString() {
        return "Hike{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", distanceKm=" + distanceKm +
                ", hiker=" + hiker.getFirstName() +
                '}';
    }
}
