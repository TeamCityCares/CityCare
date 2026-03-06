package com.cts.CityCare.CityCare.entity;

import java.util.Date;

public class Citizen {
    private long citizenId;
    private String citizenName;
    private Date citizenDateOfBirth;
    private String citizenGender;
    private String citizenAddress;
    private long citizenContactNumber;
    private String citizenStatus;


    public Citizen() {
    }

    public Citizen(long citizenId, String citizenName, Date citizenDateOfBirth, String citizenGender, String citizenAddress, long citizenContactNumber, String citizenStatus) {
        this.citizenId = citizenId;
        this.citizenName = citizenName;
        this.citizenDateOfBirth = citizenDateOfBirth;
        this.citizenGender = citizenGender;
        this.citizenAddress = citizenAddress;
        this.citizenContactNumber = citizenContactNumber;
        this.citizenStatus = citizenStatus;
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

    public Date getCitizenDateOfBirth() {
        return citizenDateOfBirth;
    }

    public void setCitizenDateOfBirth(Date citizenDateOfBirth) {
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

    public long getCitizenContactNumber() {
        return citizenContactNumber;
    }

    public void setCitizenContactNumber(long citizenContactNumber) {
        this.citizenContactNumber = citizenContactNumber;
    }

    public String getCitizenStatus() {
        return citizenStatus;
    }

    public void setCitizenStatus(String citizenStatus) {
        this.citizenStatus = citizenStatus;
    }
}
