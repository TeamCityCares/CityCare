package com.cts.CityCare.CityCare.controller;

import com.cts.CityCare.CityCare.dto.request.FacilityRequest;
import com.cts.CityCare.CityCare.dto.response.ApiResponse;
import com.cts.CityCare.CityCare.dto.response.FacilityResponse;
import com.cts.CityCare.CityCare.dto.response.StaffResponse;
import com.cts.CityCare.CityCare.entity.Facility;
import com.cts.CityCare.CityCare.service.FacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facilities")
@RequiredArgsConstructor
public class FacilityController {

    private final FacilityService facilityService;

    @PostMapping
    public ResponseEntity<ApiResponse<FacilityResponse>> create(@RequestBody FacilityRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok("Facility created", facilityService.createFacility(request)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<FacilityResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.ok("All facilities", facilityService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FacilityResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("Facility", facilityService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<FacilityResponse>> update(@PathVariable Long id, @RequestBody FacilityRequest request) {
        return ResponseEntity.ok(ApiResponse.ok("Facility updated", facilityService.updateFacility(id, request)));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<FacilityResponse>> updateStatus(@PathVariable Long id, @RequestParam Facility.Status status) {
        return ResponseEntity.ok(ApiResponse.ok("Facility status updated to " + status, facilityService.updateStatus(id, status)));
    }

    @GetMapping("/{id}/staff")
    public ResponseEntity<ApiResponse<List<StaffResponse>>> getStaff(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("Staff at facility", facilityService.getStaffByFacility(id)));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<ApiResponse<List<FacilityResponse>>> getByType(@PathVariable Facility.Type type) {
        return ResponseEntity.ok(ApiResponse.ok("Facilities by type: " + type, facilityService.getByType(type)));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<FacilityResponse>>> getByStatus(@PathVariable Facility.Status status) {
        return ResponseEntity.ok(ApiResponse.ok("Facilities with status: " + status, facilityService.getByStatus(status)));
    }
}
