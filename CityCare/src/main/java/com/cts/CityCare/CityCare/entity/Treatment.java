package com.cts.CityCare.CityCare.entity;


import java.time.LocalDateTime;



public class Treatment {


    private Long treatmentID;
    private Long patientID;
    private Long doctorID;
    private String description;
    private LocalDateTime date;
    private String status;

    // Default Constructor
    public Treatment() {}

    // Getters and Setters
    public Long getTreatmentID() { return treatmentID; }
    public void setTreatmentID(Long treatmentID) { this.treatmentID = treatmentID; }

    public Long getPatientID() { return patientID; }
    public void setPatientID(Long patientID) { this.patientID = patientID; }

    public Long getDoctorID() { return doctorID; }
    public void setDoctorID(Long doctorID) { this.doctorID = doctorID; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}