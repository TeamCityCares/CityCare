package com.cts.CityCare.CityCare.entity;

public class User {
    private Long userId;
    private String name;
    private String email;     // Used for login instead of username
    private String password;   // Necessary for the login flow
    private String role;       // e.g., "ADMIN", "DOCTOR", "CITIZEN"
    private String phone;
    private String status;     // e.g., "Active", "Inactive"
    private Long facilityId;   // Links the Administrator to their specific hospital for Normal user

    // Default Constructor
    public User() {
    }

    // Parameterized Constructor
    public User(Long userId, String name, String email, String password, String role, String phone, String status, Long facilityId) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phone = phone;
        this.status = status;
        this.facilityId = facilityId;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }
}
