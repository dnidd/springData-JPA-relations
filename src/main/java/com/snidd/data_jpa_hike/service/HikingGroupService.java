package com.snidd.data_jpa_hike.service;

import com.snidd.data_jpa_hike.dto.HikerHikingGroupDto;
import com.snidd.data_jpa_hike.dto.HikingGroupDto;
import com.snidd.data_jpa_hike.dto.HikingGroupHikerDto;
import com.snidd.data_jpa_hike.entity.Hiker;
import com.snidd.data_jpa_hike.entity.HikingGroup;
import com.snidd.data_jpa_hike.mapper.HikingGroupMapper;
import com.snidd.data_jpa_hike.repository.HikerRepository;
import com.snidd.data_jpa_hike.repository.HikingGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HikingGroupService {
    private final HikerRepository hikerRepository;
    HikingGroupRepository hikingGroupRepository;
    HikingGroupMapper hikingGroupMapper;

    public HikingGroupService(HikingGroupRepository hikingGroupRepository, HikingGroupMapper hikingGroupMapper, HikerRepository hikerRepository) {
        this.hikingGroupRepository = hikingGroupRepository;
        this.hikingGroupMapper = hikingGroupMapper;
        this.hikerRepository = hikerRepository;
    }


    /**
     * Find hiking group by id.
     * @param id id.
     * @return HikingGroupDto.
     */
    public HikingGroupDto findHikingGroupById(Integer id) {
        HikingGroup hikingGroup = hikingGroupRepository.findById(id).orElseThrow(() -> new RuntimeException("Hiking group not found"));

        return hikingGroupMapper.fromHikingGroupToHikingGroupDto(hikingGroup);
    }


    /**
     * Find hiking group by id and its hikers.
     * @param id id.
     * @return hikingGroupHikerDto - hiking group AND its hikers.
     */
    public HikingGroupHikerDto findHikingGroupByIdAndItsHikers(Integer id) {
        HikingGroup hikingGroup = hikingGroupRepository.findById(id).orElseThrow(() -> new RuntimeException("Hiking group not found"));

        HikingGroupHikerDto hikingGroupHikerDto = hikingGroupMapper.fromHikingGroupToHikingGroupHikerDto(hikingGroup);

        return hikingGroupHikerDto;
    }


    /**
     * find hiker by id and its hiking groups.
     * @param id id.
     * @return hikerHikingGroupDto - hiker AND its hiking groups.
     */
    public HikerHikingGroupDto findHikerByIdAndAssociatedHikingGroups(Long id) {
        Hiker hiker = hikerRepository.findById(id).orElseThrow(() -> new RuntimeException("Hiker not found"));

        HikerHikingGroupDto hikerHikingGroupDto = hikingGroupMapper.fromHikerToHikerHikingGroupDto(hiker);

        return hikerHikingGroupDto;
    }


}


























