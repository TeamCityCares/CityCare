package com.cts.CityCare.CityCare.dto.request;

import com.cts.CityCare.CityCare.entity.Citizen;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CitizenProfileRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    // Use lowercase 'gender' in Postman, Jackson will map it to the Enum
    private Citizen.Gender gender;

    private String address;
    private String contactInfo;
}