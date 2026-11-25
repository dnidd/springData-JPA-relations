package com.snidd.data_jpa_hike.mapper;

import com.snidd.data_jpa_hike.dto.AddressDto;
import com.snidd.data_jpa_hike.dto.AddressHikerDto;
import com.snidd.data_jpa_hike.dto.AddressRequestDto;
import com.snidd.data_jpa_hike.entity.Address;

public interface AddressMapper {

    Address fromAddressRequestDtoToAddress(AddressRequestDto addressRequestDto);

    AddressDto fromAddressToAddressDto(Address address);

    AddressHikerDto fromAddressToAddressHikerDto(Address address);
}
