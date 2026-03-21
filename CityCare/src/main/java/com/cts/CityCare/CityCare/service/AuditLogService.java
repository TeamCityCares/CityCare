package com.cts.CityCare.CityCare.service;

import com.cts.CityCare.CityCare.dto.response.AuditLogResponse;
import com.cts.CityCare.CityCare.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public List<AuditLogResponse> getAllAuditLogs() {
        return auditLogRepository.findAllLogs().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<AuditLogResponse> getAuditLogsByUser(String email) {
        return auditLogRepository.findLogsByUserEmail(email).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private AuditLogResponse mapToResponse(com.cts.CityCare.CityCare.entity.AuditLog log) {
        return AuditLogResponse.builder()
                .logId(log.getLogId())
                .userEmail(log.getUser() != null ? log.getUser().getEmail() : "system")
                .action(log.getAction())
                .resource(log.getResource())
                .timestamp(log.getTimestamp())
                .build();
    }
}
