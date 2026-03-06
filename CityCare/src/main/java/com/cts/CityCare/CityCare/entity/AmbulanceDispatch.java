package com.cts.CityCare.CityCare.entity;

import java.time.LocalDateTime;

public class AmbulanceDispatch {
    private Integer dispatchId;
    private Integer emergencyId;
    private Integer dispatcherId;
    private Integer ambulanceId;
    private LocalDateTime date;
    private String status;

    public AmbulanceDispatch() {

    }

    public AmbulanceDispatch(Integer dispatchId, Integer emergencyId, Integer dispatcherId, Integer ambulanceId, LocalDateTime date, String status) {
        this.dispatchId = dispatchId;
        this.emergencyId = emergencyId;
        this.dispatcherId = dispatcherId;
        this.ambulanceId = ambulanceId;
        this.date = date;
        this.status = status;
    }

    public Integer getDispatchId() { return dispatchId; }
    public void setDispatchId(Integer dispatchId) { this.dispatchId = dispatchId; }

    public Integer getEmergencyId() { return emergencyId; }
    public void setEmergencyId(Integer emergencyId) { this.emergencyId = emergencyId; }

    public Integer getDispatcherId() { return dispatcherId; }
    public void setDispatcherId(Integer dispatcherId) { this.dispatcherId = dispatcherId; }

    public Integer getAmbulanceId() { return ambulanceId; }
    public void setAmbulanceId(Integer ambulanceId) { this.ambulanceId = ambulanceId; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}