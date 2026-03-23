package com.cts.CityCare.CityCare.service;

import com.cts.CityCare.CityCare.dto.request.LoginRequest;
import com.cts.CityCare.CityCare.dto.request.RegisterRequest;
import com.cts.CityCare.CityCare.dto.response.AuthResponse;
import com.cts.CityCare.CityCare.entity.Citizen; // Added Import
import com.cts.CityCare.CityCare.entity.User;
import com.cts.CityCare.CityCare.repository.CitizenRepository; // Added Import
import com.cts.CityCare.CityCare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final CitizenRepository citizenRepository; // Inject CitizenRepository
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        // 1. Check if email exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered: " + request.getEmail());
        }

        // 2. Create new User entity
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .role(User.Role.CITIZEN) // Default for self-registration
                .status(User.Status.ACTIVE)
                .build();

        User savedUser = userRepository.save(user);

        // 3. MANDATORY: Create and Save the Citizen record linked to this User
        // This ensures the data appears in the 'citizens' table immediately
        Citizen citizen = Citizen.builder()
                .name(savedUser.getName())
                .contactInfo(savedUser.getPhone())
                .user(savedUser) // Linking the Foreign Key
                .status(Citizen.Status.ACTIVE)
                .build();

        citizenRepository.save(citizen);

        // 4. Return user details
        return mapToAuthResponse(savedUser);
    }

    public AuthResponse login(LoginRequest request) {
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