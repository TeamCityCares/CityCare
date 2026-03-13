package com.cts.CityCare.CityCare.repository;

import com.cts.CityCare.CityCare.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {
    List<Facility> findByType(Facility.Type type);
<<<<<<< HEAD

    List<Facility> findByStatus(Facility.Status status);

    @Query("select f from Facility f")
    List<Facility> findAllFacilityList();
    List<Facility> findAll();
=======

    List<Facility> findByStatus(Facility.Status status);


>>>>>>> 262735c36309d3b320650c608f9037622ab71601

}
