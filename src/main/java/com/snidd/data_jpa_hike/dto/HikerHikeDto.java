package com.snidd.data_jpa_hike.dto;


import java.time.LocalDate;
import java.util.Set;

public class HikerHikeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate joinDate;
    private double monthlyGoalKm;

    private Set<HikeDto> hikeDtos;

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

    public Set<HikeDto> getHikeDtos() {
        return hikeDtos;
    }

    public void setHikeDtos(Set<HikeDto> hikeDtos) {
        this.hikeDtos = hikeDtos;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("HikerHikeDto {")
                .append("\n  id=").append(id)
                .append("\n  firstName='").append(firstName).append('\'')
                .append("\n  lastName='").append(lastName).append('\'')
                .append("\n  email='").append(email).append('\'')
                .append("\n  joinDate=").append(joinDate)
                .append("\n  monthlyGoalKm=").append(monthlyGoalKm)
                .append("\n  Hikes:");

        if (hikeDtos != null && !hikeDtos.isEmpty()) {
            for (HikeDto hike : hikeDtos) {
                sb.append("\n    ").append(hike);
            }
        } else {
            sb.append("\n    No hikes registered");
        }

        sb.append("\n}");

        return sb.toString();
    }
}
