package com.snidd.data_jpa_hike.dto;

import java.util.HashSet;
import java.util.Set;

public class HikingGroupHikerDto {
    private Long id;
    private String name;


    private Set<HikerDto> hikerDtos = new HashSet<>();

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

    public Set<HikerDto> getHikerDtos() {
        return hikerDtos;
    }

    public void setHikerDtos(Set<HikerDto> hikerDtos) {
        this.hikerDtos = hikerDtos;
    }
}


