package com.cts.CityCare.CityCare.dto.response;

import com.cts.CityCare.CityCare.entity.Staff;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StaffResponse {
    private Long staffId; 
    private String name;
    private Staff.Role role;
    private String contactInfo;
    private Staff.Status status;
    private Long facilityId;
    private Long userId;
}
