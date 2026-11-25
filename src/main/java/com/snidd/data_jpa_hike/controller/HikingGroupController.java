package com.snidd.data_jpa_hike.controller;

import com.snidd.data_jpa_hike.dto.HikerHikingGroupDto;
import com.snidd.data_jpa_hike.dto.HikingGroupDto;
import com.snidd.data_jpa_hike.dto.HikingGroupHikerDto;
import com.snidd.data_jpa_hike.service.HikingGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/hikingGroup")
public class HikingGroupController {
    private final HikingGroupService hikingGroupService;

    public HikingGroupController(HikingGroupService hikingGroupService) {
        this.hikingGroupService = hikingGroupService;
    }


    /**
     * Find hiking group by id.
     * @param id id.
     * @return HikingGroupDto.
     */
    @GetMapping("/{id}")
    public ResponseEntity<HikingGroupDto> findHikingGroupById(@PathVariable Integer id) {
        HikingGroupDto hikingGroupDto = hikingGroupService.findHikingGroupById(id);

        return ResponseEntity.ok(hikingGroupDto);
    }


    // ********************
    // ---- ManyToMany ----
    // ********************

    /**
     * Find hiking group by id and its hikers.
     * @param id id.
     * @return hikingGroupHikerDto - hiking group AND its hikers.
     */
    @GetMapping("/{id}/hikingGroupHikers")
    public ResponseEntity<HikingGroupHikerDto> findHikingGroupByIdAndItsHikers(@PathVariable Integer id) {
        HikingGroupHikerDto hikingGroupHikerDto = hikingGroupService.findHikingGroupByIdAndItsHikers(id);

        return ResponseEntity.ok(hikingGroupHikerDto);
    }


    /**
     * Find hiker by id and its hiking groups.
     * @param id id.
     * @return hikerHikingGroupDto - hiker AND its hiking groups.
     */
    @GetMapping("{id}/hikerHikingGroups")
    public ResponseEntity<HikerHikingGroupDto> findHikerByIdAndAssociatedHikingGroups(@PathVariable Long id) {
        HikerHikingGroupDto hikerHikingGroupDto = hikingGroupService.findHikerByIdAndAssociatedHikingGroups(id);

        return ResponseEntity.ok(hikerHikingGroupDto);
    }

}
