package com.cts.CityCare.CityCare.repository;

import com.cts.CityCare.CityCare.entity.Ambulance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmbulanceRepository extends JpaRepository<Ambulance, Long> {

    List<Ambulance> findByStatus(Ambulance.Status status);

    boolean existsByVehicleNumber(String vehicleNumber);
}
