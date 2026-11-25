package com.snidd.data_jpa_hike.service;


import com.snidd.data_jpa_hike.dto.AddressDto;
import com.snidd.data_jpa_hike.dto.AddressHikerDto;
import com.snidd.data_jpa_hike.entity.Address;
import com.snidd.data_jpa_hike.mapper.AddressMapper;
import com.snidd.data_jpa_hike.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }


    /**
     * Get all addresses.
     * @return List of AddressDto
     */
    public List<AddressDto> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();

        List<AddressDto> addressDtos = new ArrayList<>();

        for (Address address : addresses) {
            AddressDto addressDto = addressMapper.fromAddressToAddressDto(address);
            addressDtos.add(addressDto);
        }
        return addressDtos;
    }


    /**
     * Get Address by id & associated Hiker.
     * @param id address id.
     * @return Address by id & associated Hiker.
     */
    public AddressHikerDto getAddressByIdWithHiker(Long id) {
        Address address = addressRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Address not found"));

        AddressHikerDto addressHikerDto = addressMapper.fromAddressToAddressHikerDto(address);

        return addressHikerDto;
    }
}
