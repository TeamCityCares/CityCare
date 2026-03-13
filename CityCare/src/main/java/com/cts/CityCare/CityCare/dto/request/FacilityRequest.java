package com.cts.CityCare.CityCare.dto.request;

import com.cts.CityCare.CityCare.entity.Facility;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


//we use dto classes, it will tell us, which data we want to show to the user or which  to hide

@Data
public class FacilityRequest {

    @NotBlank(message = "Name is Required")
    private String name;

    @NotNull(message = "Type is Required")
    private Facility.Type type;

    @NotBlank(message = "Location is Required")
    private String location;

    private int capacity;

    private Facility.Status status;
}
