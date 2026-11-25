package com.snidd.data_jpa_hike.repository;

import com.snidd.data_jpa_hike.entity.Hiker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface HikerRepository extends JpaRepository<Hiker, Long> {


    // JPQL (Java Persistence Query Language), not SQL. ?1 placeholder for the actual insert value.
    @Query(value = "select h from Hiker h where h.email = ?1")
    // Derived Query – Spring Data JPA interprets the method name.
    // Can be run without above JPQL.
    Optional<Hiker> findHikerByEmail(String email);

    // JPQL with named parameter.
    @Query(value = "select h from Hiker h where upper(h.lastName) = upper(:lastname)")
    List<Hiker> findHikerByLastNameIgnoreCase(@Param("lastname") String lastName);
    // JPA Derived Query (simpler).
//     List<Hiker> findHikerByLastNameIgnoreCase(String lastName);

    // JPQL and derived query.
    @Query(value = "select h from Hiker h where upper(h.firstName) = upper(?1) and upper(h.lastName) = upper(?2)")
    List<Hiker> findHikerByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);
    // Derived Query
//    List<Hiker> findHikerByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);


    // Native queries
    // last_name & monthly_goal_km: DB columns.
    // :lastname & :monthlygoalkm: named parameters.
    @Query(value = "select * from hiker where upper(last_name) = upper(:lastname) and monthly_goal_km> :monthlygoalkm",
    nativeQuery = true)
    List<Hiker> findHikerByLastNameIgnoreCaseAndMonthlyGoalKmGreaterThan(
            @Param("lastname") String lastName,
            @Param("monthlygoalkm") double monthlyGoalKm);
    // JPQL and derived query.
//    List<Hiker> findHikerByLastNameIgnoreCaseAndMonthlyGoalKmGreaterThan(String lastName, double monthlyGoalKm);


    // Native queries
    // Modifying used for modifying not selecting
    // delete queries are modifying queries, not select queries and don't return data - void.
    // @Modifying treats the query as update or delete.
    // @Transactional - delete modifies DB and needs to have @Transactional.
    @Modifying
    @Transactional
    @Query("delete from Hiker h where h.email = :email ")
    void deleteHikerByEmail(@Param("email") String email);
    // JPQL and derived query.
//    void deleteHikerByEmail(@Param("email") String email);







}
