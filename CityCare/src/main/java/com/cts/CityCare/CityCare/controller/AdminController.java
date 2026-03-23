package com.cts.CityCare.CityCare.controller;

import com.cts.CityCare.CityCare.dto.request.AmbulanceRequest;
import com.cts.CityCare.CityCare.dto.request.CreateStaffRequest;
import com.cts.CityCare.CityCare.dto.response.ApiResponse;
import com.cts.CityCare.CityCare.entity.Ambulance;
import com.cts.CityCare.CityCare.entity.User;
import com.cts.CityCare.CityCare.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // STAFF
    @PostMapping("/staff")
    public ResponseEntity<ApiResponse<User>> createStaff(@Valid @RequestBody CreateStaffRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Staff account created", adminService.createStaff(request)));
    }

    @GetMapping("/staff")
    public ResponseEntity<ApiResponse<List<User>>> getAllStaff() {
        return ResponseEntity.ok(ApiResponse.ok("All staff", adminService.getAllStaff()));
    }

    // DISPATCHERS
    @PostMapping("/dispatchers")
    public ResponseEntity<ApiResponse<User>> createDispatcher(@Valid @RequestBody CreateStaffRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Dispatcher account created", adminService.createDispatcher(request)));
    }

    @GetMapping("/dispatchers")
    public ResponseEntity<ApiResponse<List<User>>> getAllDispatchers() {
        return ResponseEntity.ok(ApiResponse.ok("All dispatchers", adminService.getAllDispatchers()));
    }

    // COMPLIANCE / HEALTH OFFICERS
    @PostMapping("/compliance-officers")
    public ResponseEntity<ApiResponse<User>> createComplianceOfficer(@Valid @RequestBody CreateStaffRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Compliance officer created", adminService.createComplianceOfficer(request)));
    }

    @PostMapping("/health-officers")
    public ResponseEntity<ApiResponse<User>> createHealthOfficer(@Valid @RequestBody CreateStaffRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("City health officer created", adminService.createCityHealthOfficer(request)));
    }

    // AMBULANCES
    @PostMapping("/ambulances")
    public ResponseEntity<ApiResponse<Ambulance>> addAmbulance(@Valid @RequestBody AmbulanceRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Ambulance added to fleet", adminService.addAmbulance(request)));
    }

    @GetMapping("/ambulances")
    public ResponseEntity<ApiResponse<List<Ambulance>>> getAllAmbulances() {
        return ResponseEntity.ok(ApiResponse.ok("All ambulances", adminService.getAllAmbulances()));
    }

    @PatchMapping("/ambulances/{id}/status")
    public ResponseEntity<ApiResponse<Ambulance>> updateAmbulanceStatus(@PathVariable Long id, @RequestParam Ambulance.Status status) {
        return ResponseEntity.ok(ApiResponse.ok("Ambulance status updated to " + status, adminService.updateAmbulanceStatus(id, status)));
    }

    // USER MANAGEMENT
    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        return ResponseEntity.ok(ApiResponse.ok("All users", adminService.getAllUsers()));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("User", adminService.getUserById(id)));
    }

    @PatchMapping("/users/{id}/deactivate")
    public ResponseEntity<ApiResponse<User>> deactivateUser(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("User deactivated", adminService.deactivateUser(id)));
    }

    @PatchMapping("/users/{id}/activate")
    public ResponseEntity<ApiResponse<User>> activateUser(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("User activated", adminService.activateUser(id)));
    }
}

