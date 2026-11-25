package com.snidd.data_jpa_hike.repository;

import com.snidd.data_jpa_hike.entity.HikingGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HikingGroupRepository extends JpaRepository<HikingGroup, Integer> {
}
