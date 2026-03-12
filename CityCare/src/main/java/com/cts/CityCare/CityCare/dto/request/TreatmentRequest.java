package com.cts.CityCare.CityCare.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TreatmentRequest {

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotBlank(message = "Treatment description is required")
    private String description;

    private String medicationName; // Optional: e.g. "Amoxicillin"
    private String dosage;         // Optional: e.g. "500mg twice daily"
}
