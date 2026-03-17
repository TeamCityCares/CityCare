package com.cts.CityCare.CityCare.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DispatchRequest {

    @NotNull(message = "Ambulance ID is required")
    private Long ambulanceId;
}
