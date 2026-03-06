package com.cts.CityCare.CityCare.entity;

import java.time.LocalDateTime;

public class Emergency {
    private Integer emergencyId;
    private Integer citizenId;
    private String type;
    private String location;
    private LocalDateTime date;
    private String status;

    public Emergency() {

    }

    public Emergency(Integer emergencyId, Integer citizenId, String type, String location, LocalDateTime date, String status) {
        this.emergencyId = emergencyId;
        this.citizenId = citizenId;
        this.type = type;
        this.location = location;
        this.date = date;
        this.status = status;
    }

    public Integer getEmergencyId() { return emergencyId; }
    public void setEmergencyId(Integer emergencyId) { this.emergencyId = emergencyId; }

    public Integer getCitizenId() { return citizenId; }
    public void setCitizenId(Integer citizenId) { this.citizenId = citizenId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}