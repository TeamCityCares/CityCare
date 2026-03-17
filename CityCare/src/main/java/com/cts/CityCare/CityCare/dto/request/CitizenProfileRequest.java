package com.cts.CityCare.CityCare.dto.request;

import com.cts.CityCare.CityCare.entity.Citizen;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Data
public class CitizenProfileRequest {
    @NotBlank(message = "Name is required")
    private String name;
    private LocalDate dateOfBirth;
    private Citizen.Gender gender;
    private String address;
    private String contactInfo;


}
