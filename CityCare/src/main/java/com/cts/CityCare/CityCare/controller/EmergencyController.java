package com.cts.CityCare.CityCare.controller;

import com.cts.CityCare.CityCare.dto.request.DispatchRequest;
import com.cts.CityCare.CityCare.dto.request.EmergencyRequest;
import com.cts.CityCare.CityCare.dto.response.ApiResponse;
import com.cts.CityCare.CityCare.entity.Ambulance;
import com.cts.CityCare.CityCare.entity.Emergency;
import com.cts.CityCare.CityCare.service.EmergencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emergencies")
@RequiredArgsConstructor
@Tag(name = "2. Emergency", description = "Step 1: Citizen reports. Step 2: Dispatcher views + assigns ambulance.")
public class EmergencyController {

    private final EmergencyService emergencyService;

    @PostMapping("/report")
    @Operation(
            summary = "[TESTING] Report an Emergency",
            description = "POST http://localhost:8080/api/emergencies/report?citizenId=1"
    )
    public ResponseEntity<ApiResponse<Emergency>> report(
            @Valid @RequestBody EmergencyRequest request,
            @RequestParam Long citizenId) { // Changed from Security to RequestParam

        Emergency emergency = emergencyService.reportEmergency(citizenId, request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Emergency reported. Help is on the way.", emergency));
    }

    @GetMapping("/pending")
    @Operation(summary = "View All Pending Emergencies")
    public ResponseEntity<ApiResponse<List<Emergency>>> getPending() {
        return ResponseEntity.ok(
                ApiResponse.ok("Pending emergencies", emergencyService.getReportedEmergencies()));
    }

    @GetMapping("/ambulances/available")
    @Operation(summary = "Get Available Ambulances")
    public ResponseEntity<ApiResponse<List<Ambulance>>> getAvailableAmbulances() {
        return ResponseEntity.ok(
                ApiResponse.ok("Available ambulances", emergencyService.getAvailableAmbulances()));
    }

    @PostMapping("/{emergencyId}/dispatch")
    @Operation(
            summary = "Assign Ambulance to Emergency",
            description = "POST http://localhost:8080/api/emergencies/1/dispatch?dispatcherId=2"
    )
    public ResponseEntity<ApiResponse<Emergency>> dispatch(
            @PathVariable Long emergencyId,
            @Valid @RequestBody DispatchRequest request,
            @RequestParam Long dispatcherId) { // Changed from Security to RequestParam

        Emergency emergency = emergencyService.dispatchAmbulance(emergencyId, dispatcherId, request);
        return ResponseEntity.ok(ApiResponse.ok("Ambulance dispatched successfully", emergency));
    }

    @GetMapping("/dispatched")
    @Operation(summary = "View Dispatched Emergencies")
    public ResponseEntity<ApiResponse<List<Emergency>>> getDispatched() {
        return ResponseEntity.ok(
                ApiResponse.ok("Dispatched emergencies – ready for patient admission",
                        emergencyService.getDispatchedEmergencies()));
    }

    @GetMapping("/my")
    @Operation(
            summary = "View My Emergency History",
            description = "GET http://localhost:8080/api/emergencies/my?citizenId=1"
    )
    public ResponseEntity<ApiResponse<List<Emergency>>> getMyCases(
            @RequestParam Long citizenId) { // Changed from Security to RequestParam

        return ResponseEntity.ok(
                ApiResponse.ok("Your emergency history", emergencyService.getMyCases(citizenId)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Emergency Detail by ID")
    public ResponseEntity<ApiResponse<Emergency>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.ok("Emergency details", emergencyService.getById(id)));
    }
}