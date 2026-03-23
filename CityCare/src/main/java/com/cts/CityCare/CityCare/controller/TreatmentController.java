package com.cts.CityCare.CityCare.controller;

import com.cts.CityCare.CityCare.dto.request.TreatmentRequest;
import com.cts.CityCare.CityCare.dto.request.TreatmentSummaryResponse;
import com.cts.CityCare.CityCare.dto.response.ApiResponse;
import com.cts.CityCare.CityCare.entity.Treatment;

import com.cts.CityCare.CityCare.service.TreatmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treatments")
@RequiredArgsConstructor
public class TreatmentController {

    private final TreatmentService treatmentService;

    @PostMapping
    public ResponseEntity<ApiResponse<Treatment>> assign(
            @Valid @RequestBody TreatmentRequest request,
            @RequestHeader(name = "staffId") Long staffId) { // Direct ID from Header

        Treatment treatment = treatmentService.assignTreatment(staffId, request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Treatment assigned by staff: " + staffId, treatment));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<ApiResponse<List<TreatmentSummaryResponse>>> getForPatient(
            @PathVariable Long patientId) {

        // Service nundi simplified DTO list vastundi
        List<TreatmentSummaryResponse> patientTreatments = treatmentService.getForPatient(patientId);

        return ResponseEntity.ok(
                ApiResponse.ok("Treatments for patient: " + patientId, patientTreatments));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Treatment>> updateStatus(
            @PathVariable Long id,
            @RequestParam Treatment.Status status) {

        Treatment treatment = treatmentService.updateStatus(id, status);
        return ResponseEntity.ok(ApiResponse.ok("Treatment status updated", treatment));
    }

    @GetMapping("/mine")
    public ResponseEntity<ApiResponse<List<TreatmentSummaryResponse>>> getMine(
            @RequestHeader(name = "staffId") Long staffId) {

        List<TreatmentSummaryResponse> myTreatments = treatmentService.getMyAssigned(staffId);
        return ResponseEntity.ok(
                ApiResponse.ok("Assigned treatments for staff: " + staffId, myTreatments));
    }
}