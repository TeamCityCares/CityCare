package com.cts.CityCare.CityCare.repository;

import com.cts.CityCare.CityCare.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByUserUserId(Long userId);
    List<AuditLog> findByResource(String resource);
}
