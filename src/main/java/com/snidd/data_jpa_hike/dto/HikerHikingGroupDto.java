package com.snidd.data_jpa_hike.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class HikerHikingGroupDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate joinDate;
    private double monthlyGoalKm;

    private Set<HikingGroupDto> hikingGroupDtos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public double getMonthlyGoalKm() {
        return monthlyGoalKm;
    }

    public void setMonthlyGoalKm(double monthlyGoalKm) {
        this.monthlyGoalKm = monthlyGoalKm;
    }

    public Set<HikingGroupDto> getHikingGroupDtos() {
        return hikingGroupDtos;
    }

    public void setHikingGroupDtos(Set<HikingGroupDto> hikingGroupDtos) {
        this.hikingGroupDtos = hikingGroupDtos;
    }
}
