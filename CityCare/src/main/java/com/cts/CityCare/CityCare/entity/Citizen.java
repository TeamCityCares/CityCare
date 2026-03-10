package com.cts.CityCare.CityCare.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "citizens")
public class Citizen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long citizenId;
    private String citizenName;
    @Column(name = "citizen_DOB")
    private LocalDate citizenDateOfBirth;
    private String citizenGender;
    private String citizenAddress;
    private String citizenContactNumber;

    @Enumerated(EnumType.STRING)
    private CitizenStatus citizenStatus = CitizenStatus.ACTIVE;

    public Citizen() {

    }

    public Citizen(long citizenId, String citizenName, LocalDate citizenDateOfBirth, String citizenGender, String citizenAddress, String citizenContactNumber) {
        this.citizenId = citizenId;
        this.citizenName = citizenName;
        this.citizenDateOfBirth = citizenDateOfBirth;
        this.citizenGender = citizenGender;
        this.citizenAddress = citizenAddress;
        this.citizenContactNumber = citizenContactNumber;
        this.citizenStatus = CitizenStatus.ACTIVE;
    }


    public long getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(long citizenId) {
        this.citizenId = citizenId;
    }

    public String getCitizenName() {
        return citizenName;
    }

    public void setCitizenName(String citizenName) {
        this.citizenName = citizenName;
    }

    public LocalDate getCitizenDateOfBirth() {
        return citizenDateOfBirth;
    }

    public void setCitizenDateOfBirth(LocalDate citizenDateOfBirth) {
        this.citizenDateOfBirth = citizenDateOfBirth;
    }

    public String getCitizenGender() {
        return citizenGender;
    }

    public void setCitizenGender(String citizenGender) {
        this.citizenGender = citizenGender;
    }

    public String getCitizenAddress() {
        return citizenAddress;
    }

    public void setCitizenAddress(String citizenAddress) {
        this.citizenAddress = citizenAddress;
    }

    public String getCitizenContactNumber() {
        return citizenContactNumber;
    }

    public void setCitizenContactNumber(String citizenContactNumber) {
        this.citizenContactNumber = citizenContactNumber;
    }

    public CitizenStatus getCitizenStatus() {
        return citizenStatus;
    }

    public void setCitizenStatus(CitizenStatus citizenStatus) {
        this.citizenStatus = citizenStatus;
    }
}
