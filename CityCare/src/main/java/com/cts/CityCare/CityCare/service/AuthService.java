package com.cts.CityCare.CityCare.service;

import com.cts.CityCare.CityCare.dto.request.LoginRequest;
import com.cts.CityCare.CityCare.dto.request.RegisterRequest;
import com.cts.CityCare.CityCare.dto.response.AuthResponse;
import com.cts.CityCare.CityCare.entity.User;
import com.cts.CityCare.CityCare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ============================================================
 * AuthService.java – Business Logic for Auth
 * ============================================================
 * * In Basic Auth mode:
 * 1. Register: Encodes password, saves user, returns User details.
 * 2. Login: This method is now simpler as it just prepares the
 * response. Validation is handled by AuthenticationManager.
 * ============================================================
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        // 1. Check if email exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered: " + request.getEmail());
        }

        // 2. Create new Citizen user
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .role(User.Role.CITIZEN) // Default for self-registration
                .build();

        User savedUser = userRepository.save(user);

        // 3. Return user details (No JWT token included)
        return mapToAuthResponse(savedUser);
    }

    public AuthResponse login(LoginRequest request) {
        // Note: The actual password check is done in AuthController
        // via authenticationManager.authenticate().
        // This method just fetches the data to return to the frontend.

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToAuthResponse(user);
    }

    private AuthResponse mapToAuthResponse(User user) {
        return AuthResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}