package com.snidd.data_jpa_hike.controller;


import com.snidd.data_jpa_hike.dto.HikeDto;
import com.snidd.data_jpa_hike.dto.HikeHikerDto;
import com.snidd.data_jpa_hike.service.HikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hike")
public class HikeController {
    private final HikeService hikeService;

    public HikeController(HikeService hikeService) {
        this.hikeService = hikeService;
    }


    /**
     * Get all hikes.
     * @return List of HikeDto.
     */
    @GetMapping()
    public ResponseEntity<List<HikeDto>> getAllHikes() {
        List<HikeDto> hikeDtos = hikeService.getAllHikes();

        return ResponseEntity.ok(hikeDtos);
    }


    /**
     * Find hikes by DistanceKm greater than.
     * In Postman/Bruno:
     * localhost:8080/api/hike/distance?distance=10
     * @param distance distance km.
     * @return List of hikeDto.
     */
    @GetMapping("/distance")
    public ResponseEntity<List<HikeDto>> findHikesByDistanceKmGreaterThan(
            @RequestParam double distance) {
        List<HikeDto> hikeDtos = hikeService.findHikesByDistanceKmGreaterThan(distance);

        return ResponseEntity.ok(hikeDtos);
    }


    /**
     * Get Hike by id, include the owner Hiker.
     * @param id id.
     * @return HikeHikerDto.
     */
    @GetMapping("/{id}/hiker")
    public ResponseEntity<HikeHikerDto> findHikeByIdWithHiker(@PathVariable Long id) {
        HikeHikerDto hikeHikerDto = hikeService.findHikeByIdWithHiker(id);

        return ResponseEntity.ok(hikeHikerDto);
    }



}
