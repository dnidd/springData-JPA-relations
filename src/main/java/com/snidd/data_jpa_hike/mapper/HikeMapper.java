package com.snidd.data_jpa_hike.mapper;

import com.snidd.data_jpa_hike.dto.HikeDto;
import com.snidd.data_jpa_hike.dto.HikeHikerDto;
import com.snidd.data_jpa_hike.dto.HikerHikeDto;
import com.snidd.data_jpa_hike.entity.Hike;
import com.snidd.data_jpa_hike.entity.Hiker;

public interface HikeMapper {

    HikeDto fromHikeToHikeDto(Hike hike);

    HikeHikerDto fromHikeToHikeHikerDto(Hike hike);
}
