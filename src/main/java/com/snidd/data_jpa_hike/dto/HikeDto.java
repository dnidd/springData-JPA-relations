package com.snidd.data_jpa_hike.dto;

import java.time.LocalDate;

/**
 * Hike exposed in presentation.
 */
public class HikeDto {
    private Long id;
    private String name;
    private LocalDate date;
    private double distanceKm;


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

    @Override
    public String toString() {
        return "HikeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", distanceKm=" + distanceKm +
                '}';
    }
}
