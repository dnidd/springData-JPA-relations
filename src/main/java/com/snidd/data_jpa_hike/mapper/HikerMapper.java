package com.snidd.data_jpa_hike.mapper;

import com.snidd.data_jpa_hike.dto.HikerAddressDto;
import com.snidd.data_jpa_hike.dto.HikerDto;
import com.snidd.data_jpa_hike.dto.HikerHikeDto;
import com.snidd.data_jpa_hike.dto.HikerRequestDto;
import com.snidd.data_jpa_hike.entity.Hiker;

public interface HikerMapper {

    Hiker fromHikerRequestDtoToHiker(HikerRequestDto hikerRequestDto);

    HikerDto fromHikerToHikerDto(Hiker hiker);

    HikerAddressDto fromHikerToHikerAddressDto(Hiker hiker);

    HikerHikeDto fromHikerToHikerHikeDto(Hiker hiker);
}
