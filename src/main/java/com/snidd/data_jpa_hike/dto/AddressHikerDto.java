package com.snidd.data_jpa_hike.dto;

public class AddressHikerDto {
    private Long id;
    private String street;
    private String city;
    private String zipCode;

    private HikerDto hikerDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public HikerDto getHikerDto() {
        return hikerDto;
    }

    public void setHikerDto(HikerDto hikerDto) {
        this.hikerDto = hikerDto;
    }


    @Override
    public String toString() {
        return "AddressHikerDto{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", hikerDto=" + (hikerDto != null
                ? "{id=" + hikerDto.getId() +
                ", firstName=" + hikerDto.getFirstName() +
                ", lastName=" + hikerDto.getLastName() +
                ", email=" + hikerDto.getEmail() +
                ", joinDate=" + hikerDto.getJoinDate() +
                ", monthlyGoalKm=" + hikerDto.getMonthlyGoalKm() + "}"
                : "null") +
                '}';
    }


}
