package com.snidd.data_jpa_hike.service;

import com.snidd.data_jpa_hike.dto.HikeDto;
import com.snidd.data_jpa_hike.dto.HikeHikerDto;
import com.snidd.data_jpa_hike.entity.Hike;
import com.snidd.data_jpa_hike.mapper.HikeMapper;
import com.snidd.data_jpa_hike.repository.HikeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HikeService {
    private final HikeRepository hikeRepository;
    private final HikeMapper hikeMapper;

    public HikeService(HikeRepository hikeRepository, HikeMapper hikeMapper) {
        this.hikeRepository = hikeRepository;
        this.hikeMapper = hikeMapper;
    }

    /**
     * Get all hikes.
     * @return List of HikeDto.
     */
    public List<HikeDto> getAllHikes() {
    List<Hike> hikes = hikeRepository.findAll();

    if (hikes.isEmpty()) {
        return new ArrayList<>();
    }

    List<HikeDto> hikeDtos = new ArrayList<>();

    for(Hike hike : hikes) {
        HikeDto hikeDto = hikeMapper.fromHikeToHikeDto(hike);
        hikeDtos.add(hikeDto);
    }
    return hikeDtos;

    }

    /**
     * Find hikes with distance greater than parameter in km.
     * @param distance distance in km.
     * @return List of hikerDto.
     */
    public List<HikeDto> findHikesByDistanceKmGreaterThan(double distance) {
        List<Hike> hikes = hikeRepository.findHikesByDistanceKmGreaterThan(distance);

        List<HikeDto> hikeDtos = new ArrayList<>();

        for (Hike hike : hikes) {
            HikeDto hikeDto = hikeMapper.fromHikeToHikeDto(hike);
            hikeDtos.add(hikeDto);
        }

        return hikeDtos;
    }


    /**
     * Get Hike by id, include the owner Hiker.
     * @param id id.
     * @return HikeHikerDto.
     */
    public HikeHikerDto findHikeByIdWithHiker(Long id) {
        Hike hike = hikeRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Hike not found"));

        HikeHikerDto hikeHikerDto = hikeMapper.fromHikeToHikeHikerDto(hike);

        return hikeHikerDto;
    }
}
