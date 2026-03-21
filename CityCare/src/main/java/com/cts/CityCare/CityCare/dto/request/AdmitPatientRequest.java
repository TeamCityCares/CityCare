package com.cts.CityCare.CityCare.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AdmitPatientRequest {

    @NotNull(message = "Emergency ID is required")
    @Positive(message = "Patient ID must be a valid positive number")
    private Long emergencyId;
    private String ward;  // e.g. "ICU", "General Ward", "Cardiology"
    private String notes; // Admission notes
}
