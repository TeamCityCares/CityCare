package com.cts.CityCare.CityCare.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DispatchRequest {

    @NotNull(message = "Ambulance ID is required")
    @Positive(message = "ambulance Id must be positive")
    @Min(value = 1,message = "ambulanceId must start from 1")
    private Long ambulanceId;
}
