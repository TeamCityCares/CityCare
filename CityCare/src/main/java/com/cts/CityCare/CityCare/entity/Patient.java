package com.cts.CityCare.CityCare.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.EntityGraph;

import java.time.LocalDateTime;


@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long patientID;
    private Long citizenID;
    private Long facilityID;
    private LocalDateTime admissionDate;
    private LocalDateTime dischargeDate;
    private String status;

    // Default Constructor
    public Patient() {}

    // Getters and Setters
    public Long getPatientID() { return patientID; }
    public void setPatientID(Long patientID) { this.patientID = patientID; }

    public Long getCitizenID() { return citizenID; }
    public void setCitizenID(Long citizenID) { this.citizenID = citizenID; }

    public Long getFacilityID() { return facilityID; }
    public void setFacilityID(Long facilityID) { this.facilityID = facilityID; }

    public LocalDateTime getAdmissionDate() { return admissionDate; }
    public void setAdmissionDate(LocalDateTime admissionDate) { this.admissionDate = admissionDate; }

    public LocalDateTime getDischargeDate() { return dischargeDate; }
    public void setDischargeDate(LocalDateTime dischargeDate) { this.dischargeDate = dischargeDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
