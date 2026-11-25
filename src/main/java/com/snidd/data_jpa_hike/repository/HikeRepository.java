package com.snidd.data_jpa_hike.repository;

import com.snidd.data_jpa_hike.dto.HikeDto;
import com.snidd.data_jpa_hike.entity.Hike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HikeRepository extends JpaRepository<Hike, Long> {
    List<Hike> findHikesByDistanceKmGreaterThan(double distanceKm);
}
