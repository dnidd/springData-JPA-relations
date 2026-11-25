package com.snidd.data_jpa_hike.repository;

import com.snidd.data_jpa_hike.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
