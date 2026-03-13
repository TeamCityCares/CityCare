package com.cts.CityCare.CityCare.service;

import com.cts.CityCare.CityCare.dto.request.TreatmentRequest;
import com.cts.CityCare.CityCare.entity.Patient;
import com.cts.CityCare.CityCare.entity.Treatment;
import com.cts.CityCare.CityCare.entity.User;
import com.cts.CityCare.CityCare.exception.BadRequestException;
import com.cts.CityCare.CityCare.exception.ResourceNotFoundException;
import com.cts.CityCare.CityCare.repository.PatientRepository;
import com.cts.CityCare.CityCare.repository.TreatmentRepository;
import com.cts.CityCare.CityCare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    @Transactional
    public Treatment assignTreatment(Long staffId, TreatmentRequest req) {
        Patient patient = patientRepository.findById(req.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient", req.getPatientId()));

        if (patient.getStatus() == Patient.Status.DISCHARGED) {
            throw new BadRequestException(
                    "Cannot assign treatment to patient " + req.getPatientId() +
                            " – they have already been discharged.");
        }

        User staff = userRepository.findById(staffId)
                .orElseThrow(() -> new ResourceNotFoundException("Staff", staffId));

        // Belt-and-suspenders check (SecurityConfig already enforces this via roles)
        if (staff.getRole() != User.Role.DOCTOR && staff.getRole() != User.Role.NURSE) {
            throw new BadRequestException("Only DOCTOR or NURSE can assign treatments");
        }

        Treatment treatment = Treatment.builder()
                .patient(patient)
                .assignedBy(staff)
                .description(req.getDescription())
                .medicationName(req.getMedicationName())
                .dosage(req.getDosage())
                .build(); // status defaults to ONGOING, startDate defaults to today

        return treatmentRepository.save(treatment);
        // Hibernate: INSERT INTO treatments (patient_id, assigned_by, description, ...)
    }

    @Transactional
    public Treatment updateStatus(Long treatmentId, Treatment.Status newStatus) {
        Treatment treatment = treatmentRepository.findById(treatmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Treatment", treatmentId));

        treatment.setStatus(newStatus);

        // Record when the treatment ended
        if (newStatus == Treatment.Status.COMPLETED || newStatus == Treatment.Status.CANCELLED) {
            treatment.setEndDate(LocalDate.now());
        }

        return treatmentRepository.save(treatment);
    }

    public List<Treatment> getForPatient(Long patientId) {
        return treatmentRepository.findByPatientPatientId(patientId);
    }

    public List<Treatment> getMyAssigned(Long staffId) {
        return treatmentRepository.findByAssignedByUserId(staffId);
    }

    public Treatment getById(Long id) {
        return treatmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Treatment", id));
    }
}
