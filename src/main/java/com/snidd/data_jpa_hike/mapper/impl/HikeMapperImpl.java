package com.snidd.data_jpa_hike.mapper.impl;

import com.snidd.data_jpa_hike.dto.HikeDto;
import com.snidd.data_jpa_hike.dto.HikeHikerDto;
import com.snidd.data_jpa_hike.dto.HikerDto;
import com.snidd.data_jpa_hike.dto.HikerHikeDto;
import com.snidd.data_jpa_hike.entity.Hike;
import com.snidd.data_jpa_hike.entity.Hiker;
import com.snidd.data_jpa_hike.mapper.HikeMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class HikeMapperImpl implements HikeMapper {

    @Override
    public HikeDto fromHikeToHikeDto(Hike hike) {
        HikeDto hikeDto = new HikeDto();
        hikeDto.setId(hike.getId());
        hikeDto.setName(hike.getName());
        hikeDto.setDate(hike.getDate());
        hikeDto.setDistanceKm(hike.getDistanceKm());

        return hikeDto;
    }


    @Override
    public HikeHikerDto fromHikeToHikeHikerDto(Hike hike) {
        HikeHikerDto hikeHikerDto = new HikeHikerDto();
        hikeHikerDto.setId(hike.getId());
        hikeHikerDto.setName(hike.getName());
        hikeHikerDto.setDate(hike.getDate());
        hikeHikerDto.setDistanceKm(hike.getDistanceKm());

        // Ta ut hikerDto från HikeDto
        HikerDto hikerDto = new HikerDto();
        hikerDto.setId(hike.getHiker().getId());
        hikerDto.setFirstName(hike.getHiker().getFirstName());
        hikerDto.setLastName(hike.getHiker().getLastName());
        hikerDto.setEmail(hike.getHiker().getEmail());
        hikerDto.setJoinDate(hike.getHiker().getJoinDate());
        hikerDto.setMonthlyGoalKm(hike.getHiker().getMonthlyGoalKm());

        hikeHikerDto.setHikerDto(hikerDto);

        return hikeHikerDto;
    }

}
