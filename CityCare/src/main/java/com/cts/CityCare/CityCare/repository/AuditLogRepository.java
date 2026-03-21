package com.cts.CityCare.CityCare.repository;

import com.cts.CityCare.CityCare.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByUserUserId(Long userId);
    List<AuditLog> findByResource(String resource);

    @Query("SELECT a FROM AuditLog a WHERE a.user.email = :email ORDER BY a.timestamp DESC")
    List<AuditLog> findLogsByUserEmail(@Param("email") String email);
    
    @Query("SELECT a FROM AuditLog a ORDER BY a.timestamp DESC")
    List<AuditLog> findAllLogs();
}
