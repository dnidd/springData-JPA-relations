package com.snidd.data_jpa_hike.dto;

import java.time.LocalDate;

public class HikeHikerDto {

    private Long id;
    private String name;
    private LocalDate date;
    private double distanceKm;

    private HikerDto hikerDto;

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

    public HikerDto getHikerDto() {
        return hikerDto;
    }

    public void setHikerDto(HikerDto hikerDto) {
        this.hikerDto = hikerDto;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("HikeHikerDto {")
                .append("\n  id=").append(id)
                .append("\n  name").append(name)
                .append("\n  date").append(date)
                .append("\n  distanceKm").append(distanceKm)
                .append("\n  firstName='").append(hikerDto.getId()).append('\'')
                .append("\n  firstName='").append(hikerDto.getFirstName()).append('\'')
                .append("\n  lastName='").append(hikerDto.getLastName()).append('\'')
                .append("\n  email='").append(hikerDto.getEmail()).append('\'')
                .append("\n  joinDate=").append(hikerDto.getJoinDate())
                .append("\n  monthlyGoalKm=").append(hikerDto.getMonthlyGoalKm())
                .append("\n  Hiker:");

        sb.append("\n}");

        return sb.toString();
    }
}
