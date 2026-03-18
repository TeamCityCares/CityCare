package com.cts.CityCare.CityCare.controller;

import com.cts.CityCare.CityCare.dto.request.LoginRequest;
import com.cts.CityCare.CityCare.dto.request.RegisterRequest;
import com.cts.CityCare.CityCare.dto.response.ApiResponse;
import com.cts.CityCare.CityCare.dto.response.AuthResponse;
import com.cts.CityCare.CityCare.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "1. Authentication", description = "Register (Citizens only) and Login (all roles). No token needed.")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(
            summary = "Citizen Self-Registration",
            description = """
            Only CITIZEN role can use this endpoint.
            Staff (Doctor/Nurse), Dispatchers, and Admins are created by Admin via /admin/* endpoints.
            
            POSTMAN → POST http://localhost:8080/api/auth/register
            Body (raw JSON):
            {
              "name": "Ravi Kumar",
              "email": "ravi@gmail.com",
              "password": "ravi1234",
              "phone": "9876543210"
            }
                     create mode 100644 CityCare/src/main/java/com/cts/CityCare/CityCare/config/SecurityConfig.java
                     create mode 100644 CityCare/src/main/java/com/cts/CityCare/CityCare/controller/AuthController.java
                     create mode 100644 CityCare/src/main/java/com/cts/CityCare/CityCare/dto/request/LoginRequest.java
                     create mode 100644 CityCare/src/main/java/com/cts/CityCare/CityCare/dto/request/RegisterRequest.java
                     create mode 100644 CityCare/src/main/java/com/cts/CityCare/CityCare/dto/response/AuthResponse.java
                     create mode 100644 CityCare/src/main/java/com/cts/CityCare/CityCare/security/JwtAuthFilter.java
                     create mode 100644 CityCare/src/main/java/com/cts/CityCare/CityCare/security/JwtUtils.java
                     create mode 100644 CityCare/src/main/java/com/cts/CityCare/CityCare/security/UserDetailsServiceImpl.java
                     create mode 100644 CityCare/src/main/java/com/cts/CityCare/CityCare/service/AuthService.java
            """
    )
    public ResponseEntity<ApiResponse<AuthResponse>> register(
            @Valid @RequestBody RegisterRequest request) {

        AuthResponse response = authService.register(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Registration successful", response));
    }

    @PostMapping("/login")
    @Operation(
            summary = "Login for ALL Roles",
            description = """
            Works for Citizen, Doctor, Nurse, Dispatcher, Admin.
            Returns JWT token + role. Frontend uses 'role' to redirect to correct dashboard.
            
            POSTMAN → POST http://localhost:8080/api/auth/login
            Body (raw JSON):
            {
              "email": "ravi@gmail.com",
              "password": "ravi1234"
            }
            
            After login: Copy the 'token' from response.
            Add to all future requests → Headers → Authorization: Bearer <token>
            """
    )
    public ResponseEntity<ApiResponse<AuthResponse>> login(
            @Valid @RequestBody LoginRequest request) {

        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(ApiResponse.ok("Login successful", response));
    }
}
