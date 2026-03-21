package com.cts.CityCare.CityCare.service;

import com.cts.CityCare.CityCare.dto.request.FacilityRequest;
import com.cts.CityCare.CityCare.dto.response.FacilityResponse;
import com.cts.CityCare.CityCare.dto.response.StaffResponse;
import com.cts.CityCare.CityCare.entity.Facility;
import com.cts.CityCare.CityCare.entity.Staff;
import com.cts.CityCare.CityCare.exception.ResourceNotFoundException;
import com.cts.CityCare.CityCare.repository.FacilityRepository;
import com.cts.CityCare.CityCare.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacilityService {

    private final FacilityRepository facilityRepository;
    private final StaffRepository staffRepository;

    @Transactional
    public FacilityResponse createFacility(FacilityRequest req) {
        Facility facility = Facility.builder()
                .name(req.getName())
                .type(req.getType())
                .location(req.getLocation())
                .capacity(req.getCapacity())
                .status(req.getStatus() != null ? req.getStatus() : Facility.Status.ACTIVE)
                .build();
        return mapToFacilityResponse(facilityRepository.save(facility));
    }

    @Transactional
    public FacilityResponse updateFacility(Long id, FacilityRequest req){
        Facility facility = facilityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facility", id));
        facility.setName(req.getName());
        facility.setType(req.getType());
        facility.setLocation(req.getLocation());
        facility.setCapacity(req.getCapacity());
        if(req.getStatus() != null) facility.setStatus(req.getStatus());
        return mapToFacilityResponse(facilityRepository.save(facility));
    }

    public FacilityResponse getById(Long id) {
        return facilityRepository.findById(id)
                .map(this::mapToFacilityResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Facility", id));
    }

    public List<FacilityResponse> getAll() {
        return facilityRepository.findAll().stream()
                .map(this::mapToFacilityResponse)
                .collect(Collectors.toList());
    }

    public List<FacilityResponse> getByStatus(Facility.Status status) {
        return facilityRepository.findByStatus(status).stream()
                .map(this::mapToFacilityResponse)
                .collect(Collectors.toList());
    }

    public List<FacilityResponse> getByType(Facility.Type type) {
        return facilityRepository.findByType(type).stream()
                .map(this::mapToFacilityResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<StaffResponse> getStaffByFacility(Long facilityId) {
        facilityRepository.findById(facilityId)
                .orElseThrow(() -> new ResourceNotFoundException("Facility", facilityId));
        return staffRepository.findByFacilityFacilityId(facilityId).stream()
                .map(this::mapToStaffResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public FacilityResponse updateStatus(Long id, Facility.Status status) {
        Facility facility = facilityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facility", id));
        facility.setStatus(status);
        return mapToFacilityResponse(facilityRepository.save(facility));
    }

    private FacilityResponse mapToFacilityResponse(Facility facility) {
        return FacilityResponse.builder()
                .facilityId(facility.getFacilityId())
                .name(facility.getName())
                .type(facility.getType())
                .location(facility.getLocation())
                .capacity(facility.getCapacity())
                .status(facility.getStatus())
                .build();
    }

    private StaffResponse mapToStaffResponse(Staff staff) {
        return StaffResponse.builder()
                .staffId(staff.getP()) // Uses 'p' safely 
                .name(staff.getName())
                .role(staff.getRole())
                .contactInfo(staff.getContactInfo())
                .status(staff.getStatus())
                .facilityId(staff.getFacility() != null ? staff.getFacility().getFacilityId() : null)
                .userId(staff.getUser() != null ? staff.getUser().getUserId() : null)
                .build();
    }
}