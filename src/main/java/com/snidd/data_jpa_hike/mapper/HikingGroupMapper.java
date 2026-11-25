package com.snidd.data_jpa_hike.mapper;

import com.snidd.data_jpa_hike.dto.HikerHikingGroupDto;
import com.snidd.data_jpa_hike.dto.HikingGroupDto;
import com.snidd.data_jpa_hike.dto.HikingGroupHikerDto;
import com.snidd.data_jpa_hike.entity.Hiker;
import com.snidd.data_jpa_hike.entity.HikingGroup;
import org.springframework.stereotype.Component;

@Component
public interface HikingGroupMapper {

    HikingGroupDto fromHikingGroupToHikingGroupDto(HikingGroup hikingGroup);

    HikerHikingGroupDto fromHikerToHikerHikingGroupDto(Hiker hiker);

    HikingGroupHikerDto fromHikingGroupToHikingGroupHikerDto(HikingGroup hikingGroup);
}
