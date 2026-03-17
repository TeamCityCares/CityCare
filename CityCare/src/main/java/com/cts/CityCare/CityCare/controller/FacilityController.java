package com.cts.CityCare.CityCare.controller;


import com.cts.CityCare.CityCare.dto.request.FacilityRequest;
import com.cts.CityCare.CityCare.dto.response.ApiResponse;
import com.cts.CityCare.CityCare.entity.Facility;
import com.cts.CityCare.CityCare.entity.Staff;
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
    public ResponseEntity<ApiResponse<Facility>> create(@RequestBody FacilityRequest request) {
        Facility facility = facilityService.createFacility(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok("Facility created", facility));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Facility>>> getAll() {
        return ResponseEntity.ok(ApiResponse.ok("All facilities", facilityService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Facility>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("Facility", facilityService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Facility>> update(@PathVariable Long id, @RequestBody FacilityRequest request) {
        Facility facility = facilityService.updateFacility(id, request);
        return ResponseEntity.ok(ApiResponse.ok("Facility updated", facility));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Facility>> updateStatus(@PathVariable Long id, @RequestParam Facility.Status status) {
        Facility facility = facilityService.updateStatus(id, status);
        return ResponseEntity.ok(ApiResponse.ok("Facility status updated to " + status, facility));
    }

    @GetMapping("/{id}/staff")
    public ResponseEntity<ApiResponse<List<Staff>>> getStaff(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("Staff at facility", facilityService.getStaffByFacility(id)));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<ApiResponse<List<Facility>>> getByType(@PathVariable Facility.Type type) {
        return ResponseEntity.ok(ApiResponse.ok("Facilities by type: " + type, facilityService.getByType(type)));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<Facility>>> getByStatus(@PathVariable Facility.Status status) {
        return ResponseEntity.ok(ApiResponse.ok("Facilities with status: " + status, facilityService.getByStatus(status)));
    }
}
