package com.cts.CityCare.CityCare.entity;

public class Staff {
    private Long staffId;
    private Long facilityId;      // Foreign key reference ID
    private String staffName;
    private String staffRole;     //"Doctor", "Nurse"
    private String staffContactInfo;
    private String staffStatus;   //"Active", "On Leave", "Inactive"

    // Reference to the Facility object this staff belongs to
    private Facility facility;

    // Default Constructor
    public Staff() {
    }

    // Parameterized Constructor
    public Staff(Long staffId, Long facilityId, String staffName, String staffRole, String staffContactInfo, String staffStatus) {
        this.staffId = staffId;
        this.facilityId = facilityId;
        this.staffName = staffName;
        this.staffRole = staffRole;
        this.staffContactInfo = staffContactInfo;
        this.staffStatus = staffStatus;
    }

    // Getters and Setters
    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }

    public String getName() {
        return staffName;
    }

    public void setName(String staffName) {
        this.staffName = staffName;
    }

    public String getRole() {
        return staffRole;
    }

    public void setRole(String staffRole) {
        this.staffRole = staffRole;
    }

    public String getContactInfo() {
        return staffContactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.staffContactInfo = staffContactInfo;
    }

    public String getStatus() {
        return staffStatus;
    }

    public void setStatus(String status) {
        this.staffStatus = staffStatus;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }
}
