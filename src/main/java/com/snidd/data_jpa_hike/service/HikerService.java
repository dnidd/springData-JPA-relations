package com.snidd.data_jpa_hike.service;


import com.snidd.data_jpa_hike.dto.*;
import com.snidd.data_jpa_hike.entity.Hiker;
import com.snidd.data_jpa_hike.mapper.impl.HikerMapperImpl;
import com.snidd.data_jpa_hike.repository.HikerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Hiker service.
 */
@Service
public class HikerService {
    private final HikerRepository hikerRepository;
    public HikerMapperImpl hikerMapper;

    /**
     * Constructor.
     * @param hikerRepository hikerRepository.
     */
    public HikerService(HikerRepository hikerRepository, HikerMapperImpl hikerMapper) {
        this.hikerRepository = hikerRepository;
        this.hikerMapper = hikerMapper;
    }


    /**
     * Register new hiker.
     * @param hikerRequestDto HikerRequestDto.
     */
    public HikerDto registerHiker(HikerRequestDto hikerRequestDto) {
        Hiker hiker = hikerMapper.fromHikerRequestDtoToHiker(hikerRequestDto);

        Hiker savedHiker = hikerRepository.save(hiker);

        return hikerMapper.fromHikerToHikerDto(savedHiker);
    }


    /**
     * Update hiker.
     * @param id id.
     * @param hikerRequestDto hikerRequestDto.
     */
    public HikerDto updateHiker(Long id, HikerRequestDto hikerRequestDto) {
        Hiker existingHiker = hikerRepository.findById(id).orElseThrow(() -> new RuntimeException("Hiker not found"));

        existingHiker.setFirstName(hikerRequestDto.getFirstName());
        existingHiker.setLastName(hikerRequestDto.getLastName());
        existingHiker.setEmail(hikerRequestDto.getEmail());
        existingHiker.setMonthlyGoalKm(hikerRequestDto.getMonthlyGoalKm());

        Hiker savedHiker = hikerRepository.save(existingHiker);

        return hikerMapper.fromHikerToHikerDto(savedHiker);
    }


    /**
     * Get total number of hikers in DB.
     * @return amount of hikers in int.
     */
    public int getTotalAmountOfHikers() {
        return (int) hikerRepository.count();
    }


    /**
     * Find hiker by id.
     * @param id id.
     * @return hiker.
     */
    public HikerDto findHikerById(Long id) {
        Hiker foundHiker = hikerRepository.findById(id).orElseThrow(() -> new RuntimeException("Hiker not found"));
        return hikerMapper.fromHikerToHikerDto(foundHiker);
    }



    /**
     * Find Hiker by email.
     * @param email email.
     * @return Hiker if exists.
     */
    public HikerDto findHikerByEmail(String email) {
        Hiker hiker = hikerRepository.findHikerByEmail(email).orElseThrow(() -> new RuntimeException("Hiker not found"));

        return hikerMapper.fromHikerToHikerDto(hiker);
    }



    /**
     * Find Hiker by last name.
     * @param lastName last name.
     * @return List of Hiker.
     */
    public List<HikerDto> findHikerByLastName(String lastName) {
        List<Hiker> hikers = hikerRepository.findHikerByLastNameIgnoreCase(lastName);

        if (hikers.isEmpty()) {
            return new ArrayList<>();
        }

        List<HikerDto> hikerDtos = new ArrayList<>();

        for(Hiker hiker : hikers) {
            HikerDto hikerDto = hikerMapper.fromHikerToHikerDto(hiker);
            hikerDtos.add(hikerDto);
        }
        return hikerDtos;
    }



    /**
     * Find hiker based on first name and last name.
     * @param firstName first name.
     * @param lastName last name.
     * @return Hiker if exists.
     */
    public List<HikerDto> findHikerByFirstNameAndLastName(String firstName, String lastName) {
        List<Hiker> hikers = hikerRepository.findHikerByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName);

        if (hikers.isEmpty()) {
            return new ArrayList<>();
        }

        List<HikerDto> hikerDtos = new ArrayList<>();

        for (Hiker hiker : hikers) {
            HikerDto hikerDto = hikerMapper.fromHikerToHikerDto(hiker);
            hikerDtos.add(hikerDto);
        }
        return hikerDtos;
    }


    /**
     * Find all hikers in DB.
     * @return List of Hiker.
     */
    public List<HikerDto> findAllHikers() {
        List<Hiker> hikers = hikerRepository.findAll();

        if (hikers.isEmpty()) {
            return new ArrayList<>();
        }

        List<HikerDto> hikerDtos = new ArrayList<>();

        for(Hiker hiker : hikers) {
            HikerDto hikerDto = hikerMapper.fromHikerToHikerDto(hiker);
            hikerDtos.add(hikerDto);
        }
        return hikerDtos;
    }


    /**
     * Find hiker by last name & monthly km greater than.
     * @param lastName last name.
     * @param monthlyGoal monthly goal greater than.
     * @return hiker with last name and monthly goal greater than.
     */
    public List<HikerDto> findHikersByLastNameAndMonthlyGoalKmGreaterThan(String lastName, Double monthlyGoal) {
        List<Hiker> hikers = hikerRepository.findHikerByLastNameIgnoreCaseAndMonthlyGoalKmGreaterThan(lastName, monthlyGoal);

        if (hikers.isEmpty()) {
            return new ArrayList<>();
        }

        List<HikerDto> hikerDtos = new ArrayList<>();
        for(Hiker hiker : hikers) {
            HikerDto hikerDto = hikerMapper.fromHikerToHikerDto(hiker);
            hikerDtos.add(hikerDto);
        }
        return hikerDtos;
    }


    /**
     * Delete hiker by id.
     * If the Hiker is associated with an address, the address will be deleted as well.
     * @param id id.
     */
    public void deleteHikerById(Long id) {
        if(!hikerRepository.existsById(id)) {
            throw new RuntimeException("Hiker not found");
        }

        hikerRepository.deleteById(id);
    }


    /**
     * Delete hiker by email.
     * @param email email
     */
    public void deleteHikerByEmail(String email) {
        Optional<Hiker> hiker = hikerRepository.findHikerByEmail(email);

        if(hiker.isEmpty()) {
            throw new RuntimeException("Hiker not found");
        }

        hikerRepository.deleteHikerByEmail(email);
    }


    /**
     * Sort hikers by parameter like first name, last name
     * and order like ASC, DESC.
     * @return sorted hikers.
     */
    public List<HikerDto> sortHikersByParamAndOrder(String sortParam, String order) {

        Sort.Direction direction;

        if(order.equalsIgnoreCase("ASC")) {
            direction = Sort.Direction.ASC;
        } else if(order.equalsIgnoreCase("DESC")) {
            direction = Sort.Direction.DESC;
        }  else {
            throw new RuntimeException("Invalid sort order");
        }

        List<Hiker> hikers = hikerRepository.findAll(Sort.by(direction, sortParam));

        List<HikerDto> hikerDtos = new ArrayList<>();

        for(Hiker hiker : hikers) {
            HikerDto hikerDto = hikerMapper.fromHikerToHikerDto(hiker);
            hikerDtos.add(hikerDto);
        }
        return hikerDtos;
    }


    /**
     * Sort hikers by parameter, order of parameter & order of MonthlyGoalKm
     * Order of precedence if two of the same name.
     * @param sortParam parameter to sort.
     * @param orderOne order of parameter.
     * @param orderTwo order of MonthlyGoalKm.
     * @return ordered hiker Dtos.
     */
    public List<HikerDto> sortHikersAndMonthlyKmGoal(String sortParam, String orderOne, String orderTwo) {

        Sort.Direction directionOne;
        Sort.Direction directionTwo;

        if(orderOne.equalsIgnoreCase("ASC")) {
            directionOne = Sort.Direction.ASC;
        } else if (orderOne.equalsIgnoreCase("DESC")) {
            directionOne = Sort.Direction.DESC;
        } else {
            throw new IllegalArgumentException("Invalid first sort order");
        }

        if(orderTwo.equalsIgnoreCase("ASC")) {
            directionTwo = Sort.Direction.ASC;
        } else if (orderTwo.equalsIgnoreCase("DESC")) {
            directionTwo = Sort.Direction.DESC;
        } else {
            throw new IllegalArgumentException("Invalid second sort order");
        }

        Sort sort = Sort.by(directionOne, sortParam).and(Sort.by(directionTwo, "monthlyGoalKm"));

        List<Hiker> hikers = hikerRepository.findAll(sort);

        List<HikerDto> hikerDtos = new ArrayList<>();

        for(Hiker hiker : hikers) {
            HikerDto hikerDto = hikerMapper.fromHikerToHikerDto(hiker);
            hikerDtos.add(hikerDto);
        }

        return hikerDtos;
    }


    /**
     * Pagination on hikers, sorted on sortBy.
     * @param pageNumber page number.
     * @param pageSize amount of elements/objects.
     * @param sortBy id, firstName etc…
     * @return Page of Hiker.
     */
    public Page<HikerDto> pageHikers(int pageNumber, int pageSize,  String sortBy) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());

        Page<Hiker> hikers = hikerRepository.findAll(pageRequest);

        List<HikerDto> hikerDtos = new ArrayList<>();

        for(Hiker hiker : hikers) {
            HikerDto hikerDto = hikerMapper.fromHikerToHikerDto(hiker);
            hikerDtos.add(hikerDto);
        }

        // PageImpl expects:
        // content – the content of this page.
        // pageable – the paging information.
        // total – the total amount of items available.
        Page<HikerDto> hikerDtoPage = new PageImpl<>(
                hikerDtos,
                hikers.getPageable(),
                hikers.getTotalElements()
                );

        return hikerDtoPage;
    }


    /**
     * Get Hiker by id & the associated Address.
     * @param id id.
     * @return HikerAddressDto.
     */
    public HikerAddressDto getHikerByIdWithAddress(Long id) {
        Hiker hiker = hikerRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Address not found"));

        HikerAddressDto hikerAddressDto = hikerMapper.fromHikerToHikerAddressDto(hiker);

        return hikerAddressDto;
    }


    // *******************
    // ---- OneToMany ----
    // *******************

    /**
     * Get hiker by id including associated  hikes.
     * @param id id.
     * @return HikerHikeDto.
     */
    public HikerHikeDto getHikerByIdWithHikes(Long id) {
        Hiker hiker = hikerRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Hiker not found"));

        HikerHikeDto hikerHikeDto = hikerMapper.fromHikerToHikerHikeDto(hiker);

        return hikerHikeDto;
    }

}
