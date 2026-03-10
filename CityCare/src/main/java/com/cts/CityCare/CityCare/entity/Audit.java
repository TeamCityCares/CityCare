package com.cts.CityCare.CityCare.entity;

import java.time.LocalDate;

public class Audit {

    private Long auditId;
    private Long officerId;
    private String scope;
    private String findings;
    private LocalDate date;
    private String status;

    public Audit(){

    }

    public Audit(Long auditId, Long officerId, String scope, String findings, LocalDate date, String status) {
        this.auditId = auditId;
        this.officerId = officerId;
        this.scope = scope;
        this.findings = findings;
        this.date = date;
        this.status = status;
    }

    public Long getAuditId() {
        return auditId;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    public Long getOfficerId() {
        return officerId;
    }

    public void setOfficerId(Long officerId) {
        this.officerId = officerId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getFindings() {
        return findings;
    }

    public void setFindings(String findings) {
        this.findings = findings;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

