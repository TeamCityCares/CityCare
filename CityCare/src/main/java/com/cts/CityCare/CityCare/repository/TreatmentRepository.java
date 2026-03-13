package com.cts.CityCare.CityCare.repository;

import com.cts.CityCare.CityCare.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {

    List<Treatment> findByPatientPatientId(Long patientId);

    List<Treatment> findByAssignedByUserId(Long staffId);

}