package com.snidd.data_jpa_hike.dto;


import java.time.LocalDate;

public class HikerAddressDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate joinDate;
    private double monthlyGoalKm;

    private AddressDto addressDto;

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

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public void setAddressDto(AddressDto addressDto) {
        this.addressDto = addressDto;
    }


    @Override
    public String toString() {
        return "HikerAddressDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", joinDate='" + joinDate + '\'' +
                ", monthlyGoalKm='" + monthlyGoalKm + '\'' +
                ", addressDto=" + (addressDto != null
                ? "{id=" + addressDto.getId() +
                ", street=" + addressDto.getStreet() +
                ", city=" + addressDto.getCity() +
                ", zipCode=" + addressDto.getZipCode() + "}"
                : "null") +
                '}';
    }



}
