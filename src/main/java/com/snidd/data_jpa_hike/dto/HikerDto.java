package com.snidd.data_jpa_hike.dto;

import java.time.LocalDate;

/**
 * Hiker exposed in presentation.
 */
public class HikerDto {
    private Long id;
    private String  firstName;
    private String  lastName;
    private String  email;
    private LocalDate joinDate;
    private double monthlyGoalKm;


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


    @Override
    public String toString() {
        return "HikerDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", joinDate=" + joinDate +
                ", monthlyGoalKm=" + monthlyGoalKm +
                '}';
    }
}
