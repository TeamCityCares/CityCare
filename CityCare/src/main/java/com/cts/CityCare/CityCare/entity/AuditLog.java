package com.cts.CityCare.CityCare.entity;

import java.time.LocalDateTime;

public class AuditLog {

    private Long auditId;
    private Long userId;
    private String action;
    private String resource;
    private LocalDateTime timestamp;

    public AuditLog(){

    }


    public AuditLog(Long auditId, Long userId, String action, String resource, LocalDateTime timestamp) {
        this.auditId = auditId;
        this.userId = userId;
        this.action = action;
        this.resource = resource;
        this.timestamp = timestamp;
    }


    public Long getAuditId() {
        return auditId;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
