package com.snidd.data_jpa_hike.dto;


/**
 * Dto for create hiker request.
 */
public class HikerRequestDto {
    private String  firstName;
    private String  lastName;
    private String  email;
    private double monthlyGoalKm;

    public double getMonthlyGoalKm() {
        return monthlyGoalKm;
    }

    public void setMonthlyGoalKm(double monthlyGoalKm) {
        this.monthlyGoalKm = monthlyGoalKm;
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
}


