package com.cts.CityCare.CityCare.repository;


import com.cts.CityCare.CityCare.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
