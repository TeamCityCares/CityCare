package com.cts.CityCare.CityCare.dto.request;

import com.cts.CityCare.CityCare.entity.Emergency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmergencyRequest {

    @NotNull(message = "Emergency type is required")
    private Emergency.Type type;  // ACCIDENT, HEART_ATTACK, FIRE, STROKE, FALL, OTHER

    @NotBlank(message = "Location is required")
    private String location;

    private String description; // Optional extra details
}