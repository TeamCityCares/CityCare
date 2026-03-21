package com.cts.CityCare.CityCare.controller;


import com.cts.CityCare.CityCare.dto.request.CitizenProfileRequest;
import com.cts.CityCare.CityCare.dto.response.ApiResponse;
import com.cts.CityCare.CityCare.entity.Citizen;
import com.cts.CityCare.CityCare.entity.CitizenDocument;
import com.cts.CityCare.CityCare.entity.User;
import com.cts.CityCare.CityCare.service.CitizenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping("/citizens")
@RequiredArgsConstructor
public class CitizenController {

    private final CitizenService citizenService;


    @PostMapping("/profile/{userId}")
    public ResponseEntity<ApiResponse<Citizen>> upsertProfile(
            @PathVariable Long userId,
            @Valid @RequestBody CitizenProfileRequest request) {
        Citizen citizen = citizenService.createOrUpdateProfile(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Profile saved", citizen));
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<ApiResponse<Citizen>> getMyProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(ApiResponse.ok("Citizen profile", citizenService.getProfile(userId)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Citizen>>> getAll() {
        return ResponseEntity.ok(ApiResponse.ok("All citizens", citizenService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Citizen>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("Citizen details", citizenService.getById(id)));
    }

    @PostMapping("/{id}/documents")
    public ResponseEntity<ApiResponse<CitizenDocument>> uploadDocument(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        try {
            byte[] documentBytes = file.getBytes();
            CitizenDocument doc = citizenService.uploadDocument(id, documentBytes);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.ok("Document uploaded", doc));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Failed to upload document: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}/documents")
    public ResponseEntity<ApiResponse<List<CitizenDocument>>> getDocuments(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("Documents", citizenService.getDocuments(id)));
    }

    @PatchMapping("/documents/{docId}/verify")
    public ResponseEntity<ApiResponse<CitizenDocument>> verifyDocument(
            @PathVariable Long docId,
            @RequestParam CitizenDocument.VerificationStatus status) {
        CitizenDocument doc = citizenService.verifyDocument(docId, status);
        return ResponseEntity.ok(ApiResponse.ok("Document status updated to " + status, doc));
    }
}
