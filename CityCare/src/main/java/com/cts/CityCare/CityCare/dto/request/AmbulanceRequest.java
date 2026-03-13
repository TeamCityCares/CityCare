package com.cts.CityCare.CityCare.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AmbulanceRequest {

    @NotBlank(message = "Vehicle number is required")
    private String vehicleNumber;
    private String model;
}