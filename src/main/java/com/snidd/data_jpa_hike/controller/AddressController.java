package com.snidd.data_jpa_hike.controller;


import com.snidd.data_jpa_hike.dto.AddressDto;
import com.snidd.data_jpa_hike.dto.AddressHikerDto;
import com.snidd.data_jpa_hike.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    AddressService addressService;

    /**
     * Constructor.
     * @param addressService addressService.
     */
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    /**
     * Get all addresses.
     * In Postman/Bruno:
     * localhost:8080/api/address
     * @return List of AddressDto
     */
    @GetMapping
    public ResponseEntity<List<AddressDto>> getAllAddresses() {
        List<AddressDto> addressDtos = addressService.getAllAddresses();

        return ResponseEntity.ok(addressDtos);
    }


    /**
     * Get Address by id & associated Hiker.
     * In Postman/Bruno:
     * localhost:8080/api/address/1
     * @param id address id.
     * @return Address by id & associated Hiker.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AddressHikerDto> getAddressByIdWithHiker(@PathVariable Long id) {
        AddressHikerDto addressHikerDto = addressService.getAddressByIdWithHiker(id);

        return ResponseEntity.ok(addressHikerDto);
    }



}
