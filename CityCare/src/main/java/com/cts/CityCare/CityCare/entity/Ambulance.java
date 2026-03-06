package com.cts.CityCare.CityCare.entity;

public class Ambulance {
    private Integer ambulanceId;
    private Integer facilityId;
    private String vehicleNumber;
    private String status;

    public Ambulance() {

    }

    public Ambulance(Integer ambulanceId, Integer facilityId, String vehicleNumber, String status) {
        this.ambulanceId = ambulanceId;
        this.facilityId = facilityId;
        this.vehicleNumber = vehicleNumber;
        this.status = status;
    }

    public Integer getAmbulanceId() { return ambulanceId; }
    public void setAmbulanceId(Integer ambulanceId) { this.ambulanceId = ambulanceId; }

    public Integer getFacilityId() { return facilityId; }
    public void setFacilityId(Integer facilityId) { this.facilityId = facilityId; }

    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}