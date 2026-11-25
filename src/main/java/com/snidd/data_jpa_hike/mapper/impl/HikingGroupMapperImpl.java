package com.snidd.data_jpa_hike.mapper.impl;

import com.snidd.data_jpa_hike.dto.HikerDto;
import com.snidd.data_jpa_hike.dto.HikerHikingGroupDto;
import com.snidd.data_jpa_hike.dto.HikingGroupDto;
import com.snidd.data_jpa_hike.dto.HikingGroupHikerDto;
import com.snidd.data_jpa_hike.entity.Hiker;
import com.snidd.data_jpa_hike.entity.HikingGroup;
import com.snidd.data_jpa_hike.mapper.HikerMapper;
import com.snidd.data_jpa_hike.mapper.HikingGroupMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class HikingGroupMapperImpl implements HikingGroupMapper {
    HikerMapper hikerMapper;

    public HikingGroupMapperImpl(HikerMapper hikerMapper) {
        this.hikerMapper = hikerMapper;
    }

    @Override
    public HikingGroupDto fromHikingGroupToHikingGroupDto(HikingGroup hikingGroup) {
        HikingGroupDto hikingGroupDto = new HikingGroupDto();
        hikingGroupDto.setId(hikingGroup.getId());
        hikingGroupDto.setName(hikingGroup.getName());

        return hikingGroupDto;
    }

    @Override
    public HikerHikingGroupDto fromHikerToHikerHikingGroupDto(Hiker hiker) {
        HikerHikingGroupDto hikerHikingGroupDto = new HikerHikingGroupDto();
        hikerHikingGroupDto.setId(hiker.getId());
        hikerHikingGroupDto.setFirstName(hiker.getFirstName());
        hikerHikingGroupDto.setLastName(hiker.getLastName());
        hikerHikingGroupDto.setEmail(hiker.getEmail());
        hikerHikingGroupDto.setJoinDate(hiker.getJoinDate());
        hikerHikingGroupDto.setMonthlyGoalKm(hiker.getMonthlyGoalKm());

        Set<HikingGroupDto> hikingGroupDtos = new HashSet<>();

        for (HikingGroup hikingGroup : hiker.getHikingGroups()) {
            HikingGroupDto hikingGroupDto = fromHikingGroupToHikingGroupDto(hikingGroup);

            hikingGroupDtos.add(hikingGroupDto);
        }

        hikerHikingGroupDto.setHikingGroupDtos(hikingGroupDtos);

        return hikerHikingGroupDto;
    }



    public HikingGroupHikerDto fromHikingGroupToHikingGroupHikerDto(HikingGroup hikingGroup) {
        HikingGroupHikerDto hikingGroupHikerDto = new HikingGroupHikerDto();

        hikingGroupHikerDto.setId(hikingGroup.getId());
        hikingGroupHikerDto.setName(hikingGroup.getName());

        Set<HikerDto> hikerDtos = new HashSet<>();

        for (Hiker hiker : hikingGroup.getHikers()) {
            HikerDto hikerDto = hikerMapper.fromHikerToHikerDto(hiker);
            hikerDtos.add(hikerDto);
        }

        hikingGroupHikerDto.setHikerDtos(hikerDtos);

        return hikingGroupHikerDto;
    }
}
