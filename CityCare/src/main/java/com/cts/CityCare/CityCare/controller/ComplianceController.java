package com.cts.CityCare.CityCare.controller;

import com.cts.CityCare.CityCare.dto.request.AuditRequest;
import com.cts.CityCare.CityCare.dto.request.ComplianceRecordRequest;
import com.cts.CityCare.CityCare.dto.response.ApiResponse;
import com.cts.CityCare.CityCare.entity.Audit;
import com.cts.CityCare.CityCare.entity.AuditLog;
import com.cts.CityCare.CityCare.entity.ComplianceRecord;
import com.cts.CityCare.CityCare.exception.ResourceNotFoundException;
import com.cts.CityCare.CityCare.repository.UserRepository;
import com.cts.CityCare.CityCare.service.ComplianceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compliance")
@RequiredArgsConstructor
public class ComplianceController {

    private final ComplianceService complianceService;
    private final UserRepository userRepository;

    // ── Compliance Records ────────────────────────────────────────────────────

    @PostMapping("/records")
    public ResponseEntity<ApiResponse<ComplianceRecord>> createRecord(
            @Valid @RequestBody ComplianceRecordRequest request,
            @RequestHeader(name = "officerId") Long officerId) { // Direct ID from Header

        ComplianceRecord record = complianceService.createRecord(officerId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Compliance record created by officer: " + officerId, record));
    }

    @GetMapping("/records")
    public ResponseEntity<ApiResponse<List<ComplianceRecord>>> getAllRecords() {
        return ResponseEntity.ok(ApiResponse.ok("All compliance records", complianceService.getAllRecords()));
    }

    @GetMapping("/records/{id}")
    public ResponseEntity<ApiResponse<ComplianceRecord>> getRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("Compliance record", complianceService.getRecordById(id)));
    }

    @GetMapping("/records/entity/{entityId}")
    public ResponseEntity<ApiResponse<List<ComplianceRecord>>> getByEntity(@PathVariable Long entityId) {
        return ResponseEntity.ok(ApiResponse.ok("Records for entity " + entityId, complianceService.getRecordsByEntity(entityId)));
    }

    @GetMapping("/records/type/{type}")
    public ResponseEntity<ApiResponse<List<ComplianceRecord>>> getByType(@PathVariable ComplianceRecord.EntityType type) {
        return ResponseEntity.ok(ApiResponse.ok("Records by type: " + type, complianceService.getRecordsByType(type)));
    }

    // ── Audits ────────────────────────────────────────────────────────────────

    @PostMapping("/audits")
    public ResponseEntity<ApiResponse<Audit>> createAudit(
            @Valid @RequestBody AuditRequest request,
            @RequestHeader(name = "officerId") Long officerId) { // Direct ID from Header

        Audit audit = complianceService.createAudit(officerId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Audit created by officer: " + officerId, audit));
    }

    @GetMapping("/audits")
    public ResponseEntity<ApiResponse<List<Audit>>> getAllAudits() {
        return ResponseEntity.ok(ApiResponse.ok("All audits", complianceService.getAllAudits()));
    }

    @GetMapping("/audits/{id}")
    public ResponseEntity<ApiResponse<Audit>> getAudit(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("Audit details", complianceService.getAuditById(id)));
    }

    @PatchMapping("/audits/{id}/status")
    public ResponseEntity<ApiResponse<Audit>> updateAuditStatus(
            @PathVariable Long id,
            @RequestParam Audit.Status status,
            @RequestParam(required = false) String findings) {
        Audit audit = complianceService.updateAuditStatus(id, status, findings);
        return ResponseEntity.ok(ApiResponse.ok("Audit updated", audit));
    }

    // ── Audit Logs ────────────────────────────────────────────────────────────

    @GetMapping("/logs")
    public ResponseEntity<ApiResponse<List<AuditLog>>> getLogs() {
        return ResponseEntity.ok(ApiResponse.ok("Audit logs", complianceService.getAllLogs()));
    }

    @GetMapping("/logs/user/{userId}")
    public ResponseEntity<ApiResponse<List<AuditLog>>> getLogsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(ApiResponse.ok("Logs for user " + userId, complianceService.getLogsByUser(userId)));
    }
}
