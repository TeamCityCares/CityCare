package com.cts.CityCare.CityCare.controller;

import com.cts.CityCare.CityCare.dto.response.AuditLogResponse;
import com.cts.CityCare.CityCare.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit-logs")
@RequiredArgsConstructor
public class AuditLogController {

    private final AuditLogService auditLogService;

    @GetMapping
    @PreAuthorize("hasAnyRole('CITY_HEALTH_OFFICER', 'ADMIN', 'COMPLIANCE_OFFICER')")
    public ResponseEntity<List<AuditLogResponse>> getAllLogs() {
        return ResponseEntity.ok(auditLogService.getAllAuditLogs());
    }

    @GetMapping("/user/{email}")
    @PreAuthorize("hasAnyRole('CITY_HEALTH_OFFICER', 'ADMIN', 'COMPLIANCE_OFFICER')")
    public ResponseEntity<List<AuditLogResponse>> getLogsByUser(@PathVariable String email) {
        return ResponseEntity.ok(auditLogService.getAuditLogsByUser(email));
    }
}
