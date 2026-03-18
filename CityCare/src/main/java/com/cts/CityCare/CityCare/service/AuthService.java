package com.cts.CityCare.CityCare.service;

import com.cts.CityCare.CityCare.dto.request.LoginRequest;
import com.cts.CityCare.CityCare.dto.request.RegisterRequest;
import com.cts.CityCare.CityCare.dto.response.AuthResponse;
import com.cts.CityCare.CityCare.entity.User;
import com.cts.CityCare.CityCare.exception.BadRequestException;
import com.cts.CityCare.CityCare.repository.UserRepository;
import com.cts.CityCare.CityCare.security.JwtUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthResponse register(RegisterRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new BadRequestException("Email already registered: " + req.getEmail());
        }

        User user = User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword())) // BCrypt hash
                .role(User.Role.CITIZEN) // Hardcoded – cannot self-promote to ADMIN
                .phone(req.getPhone())
                .build();

        userRepository.save(user); // Hibernate: INSERT INTO users (...)

        return AuthResponse.builder()
                .token(jwtUtils.generateToken(user.getEmail()))
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public AuthResponse login(LoginRequest req) {
        // Spring Security validates email+password → throws BadCredentialsException if invalid
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);

        User user = userRepository.findByEmail(req.getEmail()).orElseThrow();

        return AuthResponse.builder()
                .token(jwtUtils.generateToken(user.getEmail()))
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole()) // Frontend checks this to decide which page to show
                .build();
    }
}
