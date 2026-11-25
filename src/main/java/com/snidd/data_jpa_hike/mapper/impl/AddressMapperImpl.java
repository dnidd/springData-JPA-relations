package com.snidd.data_jpa_hike.mapper.impl;

import com.snidd.data_jpa_hike.dto.AddressDto;
import com.snidd.data_jpa_hike.dto.AddressHikerDto;
import com.snidd.data_jpa_hike.dto.AddressRequestDto;
import com.snidd.data_jpa_hike.entity.Address;
import com.snidd.data_jpa_hike.mapper.AddressMapper;
import com.snidd.data_jpa_hike.mapper.HikerMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


/**
 * AddressMapperImpl maps from and to Dto and entity.
 */
@Component
public class AddressMapperImpl implements AddressMapper {
    private final HikerMapper hikerMapper;

    /**
     * Constructor.
     * hikerMapper is @Lazy to break circular dependency when in HikerService
     * fetching Hiker by id & the associated Address.
     * AddressMapper calls HikerMapper and HikerMapper calls AddressMapper.
     * Lazy tells Spring: Start the other things first, and wait with sending in HikerMapper until its really needed.
     * @param hikerMapper HikerMapper.
     */
    public AddressMapperImpl(@Lazy HikerMapper hikerMapper) {
        this.hikerMapper = hikerMapper;
    }

    @Override
    public Address fromAddressRequestDtoToAddress(AddressRequestDto addressRequestDto) {
        Address address = new Address();
        address.setStreet(addressRequestDto.getStreet());
        address.setCity(addressRequestDto.getCity());
        address.setZipCode(addressRequestDto.getZipCode());

        return address;
    }


    @Override
    public AddressDto fromAddressToAddressDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setStreet(address.getStreet());
        addressDto.setCity(address.getCity());
        addressDto.setZipCode(address.getZipCode());

        return addressDto;
    }

    /**
     * Map Address(Hiker) to AddressHikerDto.
     * Get address id and fetch Address & associated Hiker
     * @param address address.
     * @return addressHikerDto.
     */
    @Override
    public AddressHikerDto fromAddressToAddressHikerDto(Address address) {
        AddressHikerDto addressHikerDto = new AddressHikerDto();
        addressHikerDto.setId(address.getId());
        addressHikerDto.setStreet(address.getStreet());
        addressHikerDto.setCity(address.getCity());
        addressHikerDto.setZipCode(address.getZipCode());

        addressHikerDto.setHikerDto(hikerMapper.fromHikerToHikerDto(address.getHiker()));

        return addressHikerDto;
    }

}
