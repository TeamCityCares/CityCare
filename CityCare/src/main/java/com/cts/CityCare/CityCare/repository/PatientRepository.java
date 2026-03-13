package com.cts.CityCare.CityCare.repository;


import com.cts.CityCare.CityCare.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByEmergencyEmergencyId(Long emergencyId);

    List<Patient> findByCitizenUserId(Long citizenId);

    List<Patient> findByStatus(Patient.Status status);

}
