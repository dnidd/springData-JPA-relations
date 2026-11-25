package com.snidd.data_jpa_hike.controller;


import com.snidd.data_jpa_hike.dto.*;
import com.snidd.data_jpa_hike.service.HikerService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/hiker")
public class HikerController {
    private final HikerService hikerService;

    /**
     * Constructor.
     * @param hikerService hikerService.
     */
    public HikerController(HikerService hikerService) {
        this.hikerService = hikerService;
    }


    /**
     * Register hiker.
     * @param hikerRequestDto hikerRequestDto.
     * @return ResponseEntity & hikerDto.
     */
    @PostMapping
    public ResponseEntity<HikerDto> registerHiker(@RequestBody HikerRequestDto hikerRequestDto) {

        HikerDto hikerDto = hikerService.registerHiker(hikerRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(hikerDto);
    }


    /**
     * Update hiker.
     * @param hikerRequestDto hikerRequestDto.
     * @param id id.
     * @return ResponseEntity.
     */
    @PutMapping("/{id}")
    public ResponseEntity<HikerDto> updateHiker(
            @RequestBody HikerRequestDto hikerRequestDto,
            @PathVariable Long id) {
        HikerDto updatedHiker = hikerService.updateHiker(id, hikerRequestDto);

        return ResponseEntity.ok(updatedHiker);
    }


    /**
     * Get total number of hikers in DB.
     * @return amount of hikers in int.
     */
    @GetMapping("/count")
    public ResponseEntity<Integer> getTotalAmountOfHikers() {
        int hikersCount = hikerService.getTotalAmountOfHikers();

        return ResponseEntity.ok(hikersCount);
    }


    /**
     * Find hiker by id.
     * In Postman/Bruno:
     * localhost:8080/api/1
     * @param id id.
     * @return hiker dto.
     */
    @GetMapping("/{id}")
    public ResponseEntity<HikerDto> findHikerById(@PathVariable Long id) {
        HikerDto hikerDto = hikerService.findHikerById(id);

        return ResponseEntity.ok(hikerDto);
    }


    /**
     * Find Hiker by email.
     * In Postman/Bruno:
     * localhost:8080/api/hiker/email?email=jon@mail.com
     * @param email email.
     * @return HikerDto if exists.
     */
    @GetMapping("/email")
    public ResponseEntity<HikerDto> findHikerByEmail(@RequestParam String email) {
        HikerDto hikerDto = hikerService.findHikerByEmail(email);

        return ResponseEntity.ok(hikerDto);
    }


    /**
     * Find Hiker by last name.
     * In Postman/Bruno:
     * localhost:8080/api/hiker/lastname?lastName=doe
     * @param lastName last name
     * @return HikerDto if exists.
     */
    @GetMapping("/lastname")
    public ResponseEntity<List<HikerDto>> findHikerByLastName(@RequestParam String lastName) {

        List<HikerDto> hikerDtos = hikerService.findHikerByLastName(lastName);

        return ResponseEntity.ok(hikerDtos);
    }


    /**
     * Find hiker based on first name and last name.
     * In Postman/Bruno:
     * localhost:8080/api/hiker/fullname?firstName=mia&lastName=palace
     * @param firstName first name.
     * @param lastName last name.
     * @return Hiker if exists.
     */
    @GetMapping("/fullname")
    public ResponseEntity<List<HikerDto>> findHikerByFirstNameAndLastName(
            @RequestParam String firstName,
            @RequestParam String lastName) {
        List<HikerDto> hikerDtos = hikerService.findHikerByFirstNameAndLastName(firstName, lastName);


        return ResponseEntity.ok(hikerDtos);
    }


    /**
     * Find all hikers in DB.
     * @return List of HikerDto.
     */
    @GetMapping
    public ResponseEntity<List<HikerDto>> findAllHikers() {
        List<HikerDto> hikerDtos = hikerService.findAllHikers();

        return ResponseEntity.ok(hikerDtos);
    }


    /**
     * Find hiker by last name & monthly goal Km greater than.
     * In Postman/Bruno:
     * localhost:8080/api/hiker/lastnamegoalgt?lastName=doe&monthlyGoalKm=60
     * @param lastName last name.
     * @param monthlyGoalKm monthly goal greater than.
     * @return List of HikerDto with last name and monthly goal greater than.
     */
    @GetMapping("/lastnamegoalgt")
    public ResponseEntity<List<HikerDto>> findHikerByLastNameAndMonthlyGoalKmGreaterThan(
            @RequestParam String lastName,
            @RequestParam Double monthlyGoalKm) {
        List<HikerDto> hikerDtos = hikerService.findHikersByLastNameAndMonthlyGoalKmGreaterThan(lastName, monthlyGoalKm);

        return ResponseEntity.ok(hikerDtos);
    }


    /**
     * Delete hiker by id.
     * If the Hiker is associated with an address, the address will be deleted as well.
     * @param id id
     * @return 204 No Content, deletion succeeded.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHiker(@PathVariable Long id) {
        hikerService.deleteHikerById(id);
        return ResponseEntity.noContent().build();
    }


    /**
     * Delete hiker by email.
     * Hiker need to NOT be associated with another entity to be able to be deleted.
     * In Postman/Bruno:
     * localhost:8080/api/hiker/email?email=jon@mail.com
     * @param email email.
     * @return 204 No Content, deletion succeeded.
     */
    @DeleteMapping("/email")
    public ResponseEntity<Void> deleteHikerByEmail(@RequestParam String email) {
        hikerService.deleteHikerByEmail(email);
        return ResponseEntity.noContent().build();
    }


    /**
     * Sort hikers by parameter like first name, last name
     * and order like ASC, DESC.
     * In Postman/Bruno:
     * localhost:8080/api/hiker/sortHikers?sortParam=firstName&order=asc
     * localhost:8080/api/hiker/sortHikers?sortParam=lastName&order=desc
     * @return sorted hiker dto's.
     */
    @GetMapping("/sortHikers")
    public ResponseEntity<List<HikerDto>> sortHikersByParamAndOrder(
            @RequestParam String sortParam,
            @RequestParam String order
    ) {
        List<HikerDto> hikerDtos = hikerService.sortHikersByParamAndOrder(sortParam, order);
        return ResponseEntity.ok(hikerDtos);
    }


    /**
     * Sort hikers by parameter (firstName, lastName…), order of parameter & order of MonthlyGoalKm.
     * Order of precedence if two of the same name.
     * In Postman/Bruno:
     * localhost:8080/api/hiker/sort/monthly-goal?sortParam=firstName&orderOne=asc&orderTwo=desc
     * localhost:8080/api/hiker/sort/monthly-goal?sortParam=firstName&orderOne=desc&orderTwo=asc
     * @param sortParam parameter to sort.
     * @param orderOne order of parameter.
     * @param orderTwo order of MonthlyGoalKm.
     * @return ordered hiker Dtos.
     */
    @GetMapping("/sort/monthly-goal")
    public ResponseEntity<List<HikerDto>> sortHikersByParameterAndMonthlyGoalKm(
            @RequestParam String sortParam,
            @RequestParam String orderOne,
            @RequestParam String orderTwo
    ) {
        List<HikerDto> hikerDtos = hikerService.sortHikersAndMonthlyKmGoal(sortParam, orderOne, orderTwo);

        return ResponseEntity.ok(hikerDtos);
    }



    /**
     * Pagination on hikers, sorted on sortBy.
     * In Postman/Bruno:
     * localhost:8080/api/hiker/page?pageNumber=3&pageSize=5&sortBy=id
     * @param pageNumber page number.
     * @param pageSize amount of elements/objects.
     * @param sortBy id, firstName etc…
     * @return Page of HikerDto.
     */
    @GetMapping("/page")
    public ResponseEntity<Page<HikerDto>> pageHikers(
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize,
            @RequestParam String sortBy) {

        Page<HikerDto> hikerDtosPage = hikerService.pageHikers(pageNumber, pageSize, sortBy);

        return ResponseEntity.ok(hikerDtosPage);
    }


    /**
     * Get Hiker by id & the associated Address.
     * In DB, a hiker needs an address for this to be useful.
     * In Postman/Bruno:
     * localhost:8080/api/address/1
     * @param id id.
     * @return HikerAddressDto.
     */
    @GetMapping("/{id}/address")
    public ResponseEntity<HikerAddressDto> getHikerByIdWithAddress(@PathVariable Long id) {
        HikerAddressDto hikerAddressDto = hikerService.getHikerByIdWithAddress(id);

        return ResponseEntity.ok(hikerAddressDto);
    }


    // *******************
    // ---- OneToMany ----
    // *******************

    /**
     * Get hiker by id including associated  hikes.
     * @param id id.
     * @return HikerHikeDto.
     */
    @GetMapping("/{id}/hike")
    public ResponseEntity<HikerHikeDto> getHikerByIdWithHikes(@PathVariable Long id) {
        HikerHikeDto hikerHikeDto = hikerService.getHikerByIdWithHikes(id);

        return ResponseEntity.ok(hikerHikeDto);
    }




}
