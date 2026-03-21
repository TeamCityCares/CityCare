package com.cts.CityCare.CityCare.dto.response;

import com.cts.CityCare.CityCare.entity.Facility;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FacilityResponse {
    private Long facilityId;
    private String name;
    private Facility.Type type;
    private String location;
    private int capacity;
    private Facility.Status status;
}
