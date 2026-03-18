package com.cts.CityCare.CityCare.controller;

import com.cts.CityCare.CityCare.dto.request.AdmitPatientRequest;
import com.cts.CityCare.CityCare.dto.response.ApiResponse;
import com.cts.CityCare.CityCare.entity.Patient;
import com.cts.CityCare.CityCare.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping("/admit")
    public ResponseEntity<ApiResponse<Patient>> admit(
            @Valid @RequestBody AdmitPatientRequest request) {

        Patient patient = patientService.admitPatient(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Patient admitted successfully. Ambulance released.", patient));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Patient>>> getAll() {
        return ResponseEntity.ok(ApiResponse.ok("All patients", patientService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Patient>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("Patient details", patientService.getById(id)));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Patient>> updateStatus(
            @PathVariable Long id,
            @RequestParam Patient.Status status) {

        Patient patient = patientService.updateStatus(id, status);
        return ResponseEntity.ok(ApiResponse.ok("Patient status updated to " + status, patient));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<Patient>>> getByStatus(
            @PathVariable Patient.Status status) {

        return ResponseEntity.ok(
                ApiResponse.ok("Patients with status: " + status, patientService.getByStatus(status)));
    }
}