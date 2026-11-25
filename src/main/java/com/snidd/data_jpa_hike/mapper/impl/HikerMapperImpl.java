package com.snidd.data_jpa_hike.mapper.impl;

import com.snidd.data_jpa_hike.dto.*;
import com.snidd.data_jpa_hike.entity.Hike;
import com.snidd.data_jpa_hike.entity.Hiker;
import com.snidd.data_jpa_hike.mapper.AddressMapper;
import com.snidd.data_jpa_hike.mapper.HikerMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * HikerMapperImpl maps from and to Dto and entity.
 */
@Component
public class HikerMapperImpl implements HikerMapper {

    private final AddressMapper addressMapper;

    public HikerMapperImpl(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Override
    public Hiker fromHikerRequestDtoToHiker(HikerRequestDto hikerRequestDto) {
        Hiker hiker = new Hiker();
        hiker.setFirstName(hikerRequestDto.getFirstName());
        hiker.setLastName(hikerRequestDto.getLastName());
        hiker.setEmail(hikerRequestDto.getEmail());
        hiker.setJoinDate(LocalDate.now());
        hiker.setMonthlyGoalKm(hikerRequestDto.getMonthlyGoalKm());

        return hiker;
    }

    @Override
    public HikerDto fromHikerToHikerDto(Hiker hiker) {
        HikerDto hikerDto = new HikerDto();
        hikerDto.setId(hiker.getId());
        hikerDto.setFirstName(hiker.getFirstName());
        hikerDto.setLastName(hiker.getLastName());
        hikerDto.setEmail(hiker.getEmail());
        hikerDto.setJoinDate(hiker.getJoinDate());
        hikerDto.setMonthlyGoalKm(hiker.getMonthlyGoalKm());

        return hikerDto;
    }


    //  Get hiker id and associated address
    @Override
    public HikerAddressDto fromHikerToHikerAddressDto(Hiker hiker) {
        HikerAddressDto hikerAddressDto = new HikerAddressDto();
        hikerAddressDto.setId(hiker.getId());
        hikerAddressDto.setFirstName(hiker.getFirstName());
        hikerAddressDto.setLastName(hiker.getLastName());
        hikerAddressDto.setEmail(hiker.getEmail());
        hikerAddressDto.setJoinDate(hiker.getJoinDate());
        hikerAddressDto.setMonthlyGoalKm(hiker.getMonthlyGoalKm());

        hikerAddressDto.setAddressDto(addressMapper.fromAddressToAddressDto(hiker.getAddress()));

        return hikerAddressDto;
    }


    @Override
    public HikerHikeDto fromHikerToHikerHikeDto(Hiker hiker) {
        HikerHikeDto hikerHikeDto = new HikerHikeDto();
        hikerHikeDto.setId(hiker.getId());
        hikerHikeDto.setFirstName(hiker.getFirstName());
        hikerHikeDto.setLastName(hiker.getLastName());
        hikerHikeDto.setEmail(hiker.getEmail());
        hikerHikeDto.setJoinDate(hiker.getJoinDate());
        hikerHikeDto.setMonthlyGoalKm(hiker.getMonthlyGoalKm());

        hikerHikeDto.setHikeDtos(fromHikeToHikeDto(hiker));

        return hikerHikeDto;
    }


    /**
     * Helper mapper method from Hike to HikeDto
     * @param hiker hiker.
     * @return hikeDto.
     */
    private Set<HikeDto> fromHikeToHikeDto(Hiker hiker) {
        Set<Hike> hikes = hiker.getHikes();

        Set<HikeDto> hikeDtos = new HashSet<>();

        for (Hike hike : hikes) {
            HikeDto hikeDto = new HikeDto();
            hikeDto.setId(hike.getId());
            hikeDto.setName(hike.getName());
            hikeDto.setDate(hike.getDate());
            hikeDto.setDistanceKm(hike.getDistanceKm());
            hikeDtos.add(hikeDto);
        }
        return hikeDtos;
    }




}
