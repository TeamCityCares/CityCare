package com.cts.CityCare.CityCare.repository;


import com.cts.CityCare.CityCare.entity.Emergency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmergencyRepository extends JpaRepository<Emergency, Long> {

    List<Emergency> findByStatus(Emergency.Status status);

    // Newest emergencies appear first – important for dispatcher alert list
    List<Emergency> findByStatusOrderByCreatedAtDesc(Emergency.Status status);

    List<Emergency> findByCitizenUserId(Long citizenId);

    List<Emergency> findByDispatcherUserId(Long dispatcherId);
}
