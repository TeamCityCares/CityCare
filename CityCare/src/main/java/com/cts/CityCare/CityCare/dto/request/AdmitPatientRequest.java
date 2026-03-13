package com.cts.CityCare.CityCare.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdmitPatientRequest {

    @NotNull(message = "Emergency ID is required")
    private Long emergencyId;

    private String ward;  // e.g. "ICU", "General Ward", "Cardiology"
    private String notes; // Admission notes
}
