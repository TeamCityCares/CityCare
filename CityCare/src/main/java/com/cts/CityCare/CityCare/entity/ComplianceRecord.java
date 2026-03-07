package com.cts.CityCare.CityCare.entity;

import java.time.LocalDate;

public class ComplianceRecord {

    private Long complianceId;
    private Long entityId;
    private String type;
    private String result;
    private LocalDate date;
    private String notes;

    public ComplianceRecord(){

    }

    public ComplianceRecord(Long complianceId, Long entityId, String type, String result, LocalDate date, String notes) {
        this.complianceId = complianceId;
        this.entityId = entityId;
        this.type = type;
        this.result = result;
        this.date = date;
        this.notes = notes;
    }

    public Long getComplianceId() {
        return complianceId;
    }

    public void setComplianceId(Long complianceId) {
        this.complianceId = complianceId;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
}