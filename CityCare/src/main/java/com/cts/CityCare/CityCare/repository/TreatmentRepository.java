package com.cts.CityCare.CityCare.repository;

import com.cts.CityCare.CityCare.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {


}