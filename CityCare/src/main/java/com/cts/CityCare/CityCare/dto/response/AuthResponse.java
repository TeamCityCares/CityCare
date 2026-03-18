package com.cts.CityCare.CityCare.dto.response;


import com.cts.CityCare.CityCare.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private Long userId;
    private String name;
    private String email;
    private User.Role role;
}