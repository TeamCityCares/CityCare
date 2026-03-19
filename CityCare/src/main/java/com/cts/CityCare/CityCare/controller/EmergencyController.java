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
public class EmergencyController {

    private final EmergencyService emergencyService;

    @PostMapping("/report")
    public ResponseEntity<ApiResponse<Emergency>> report(
            @Valid @RequestBody EmergencyRequest request,
            @RequestParam Long citizenId) {

        Emergency emergency = emergencyService.reportEmergency(citizenId, request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Emergency reported. Help is on the way.", emergency));
    }

    @GetMapping("/pending")
    public ResponseEntity<ApiResponse<List<Emergency>>> getPending() {
        return ResponseEntity.ok(
                ApiResponse.ok("Pending emergencies", emergencyService.getReportedEmergencies()));
    }

    @GetMapping("/ambulances/available")
    public ResponseEntity<ApiResponse<List<Ambulance>>> getAvailableAmbulances() {
        return ResponseEntity.ok(
                ApiResponse.ok("Available ambulances", emergencyService.getAvailableAmbulances()));
    }

    @PostMapping("/{emergencyId}/dispatch")
    public ResponseEntity<ApiResponse<Emergency>> dispatch(
            @PathVariable Long emergencyId,
            @Valid @RequestBody DispatchRequest request,
            @RequestParam Long dispatcherId) { // Changed from Security to RequestParam

        Emergency emergency = emergencyService.dispatchAmbulance(emergencyId, dispatcherId, request);
        return ResponseEntity.ok(ApiResponse.ok("Ambulance dispatched successfully", emergency));
    }

    @GetMapping("/dispatched")
    public ResponseEntity<ApiResponse<List<Emergency>>> getDispatched() {
        return ResponseEntity.ok(
                ApiResponse.ok("Dispatched emergencies – ready for patient admission",
                        emergencyService.getDispatchedEmergencies()));
    }

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<Emergency>>> getMyCases(
            @RequestParam Long citizenId) { // Changed from Security to RequestParam

        return ResponseEntity.ok(
                ApiResponse.ok("Your emergency history", emergencyService.getMyCases(citizenId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Emergency>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.ok("Emergency details", emergencyService.getById(id)));
    }
}