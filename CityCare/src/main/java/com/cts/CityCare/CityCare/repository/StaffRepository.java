package com.cts.CityCare.CityCare.repository;

import com.cts.CityCare.CityCare.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

}
