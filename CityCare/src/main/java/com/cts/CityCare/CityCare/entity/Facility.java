package com.cts.CityCare.CityCare.entity;

import java.util.List;

public class Facility {
    private Long facilityId;
    private String name;
    private String type;    // "Hospital" or "Clinic"
    private String location;
    private int totalCapacity;
    private String status;   //  "Active", "Diverting", "Maintenance"

    // List to hold staff members associated with this facility
    private List<Staff> staffMembers;

    // Default Constructor
    public Facility() {
    }

    // Parameterized Constructor
    public Facility(Long facilityId, String name, String type, String location, int totalCapacity, String status) {
        this.facilityId = facilityId;
        this.name = name;
        this.type = type;
        this.location = location;
        this.totalCapacity = totalCapacity;
        this.status = status;
    }

    // Getters and Setters
    public Long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Staff> getStaffMembers() {
        return staffMembers;
    }

    public void setStaffMembers(List<Staff> staffMembers) {
        this.staffMembers = staffMembers;
    }
}
