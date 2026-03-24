package com.cts.CityCare.CityCare.service;

import com.cts.CityCare.CityCare.dto.request.DispatchRequest;
import com.cts.CityCare.CityCare.dto.request.EmergencyRequest;
import com.cts.CityCare.CityCare.entity.Ambulance;
import com.cts.CityCare.CityCare.entity.Emergency;
import com.cts.CityCare.CityCare.entity.User;
import com.cts.CityCare.CityCare.exception.BadRequestException;
import com.cts.CityCare.CityCare.exception.ResourceNotFoundException;
import com.cts.CityCare.CityCare.repository.AmbulanceRepository;
import com.cts.CityCare.CityCare.repository.EmergencyRepository;
import com.cts.CityCare.CityCare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmergencyService {

    private final EmergencyRepository emergencyRepository;
    private final AmbulanceRepository ambulanceRepository;
    private final UserRepository userRepository;

    @Transactional
    public Emergency reportEmergency(Long citizenId, EmergencyRequest req) {
        User citizen = userRepository.findById(citizenId)
                .orElseThrow(() -> new ResourceNotFoundException("User", citizenId));

        Emergency emergency = Emergency.builder()
                .citizen(citizen)
                .type(req.getType())
                .location(req.getLocation())
                .description(req.getDescription())
                .status(Emergency.Status.REPORTED)
                .build();

        return emergencyRepository.save(emergency);
        // Hibernate: INSERT INTO emergencies (citizen_id, type, location, status, ...)
    }

    public List<Ambulance> getAvailableAmbulances() {
        return ambulanceRepository.findByStatus(Ambulance.Status.AVAILABLE);
    }

    @Transactional
    public Emergency dispatchAmbulance(Long emergencyId, Long dispatcherId, DispatchRequest req) {
        Emergency emergency = emergencyRepository.findById(emergencyId)
                .orElseThrow(() -> new ResourceNotFoundException("Emergency", emergencyId));

        if (emergency.getStatus() != Emergency.Status.REPORTED) {
            throw new BadRequestException(
                    "Cannot dispatch – emergency is already " + emergency.getStatus() +
                            ". Only REPORTED emergencies can be dispatched.");
        }

        Ambulance ambulance = ambulanceRepository.findById(req.getAmbulanceId())
                .orElseThrow(() -> new ResourceNotFoundException("Ambulance", req.getAmbulanceId()));

        if (ambulance.getStatus() != Ambulance.Status.AVAILABLE) {
            throw new BadRequestException(
                    "Ambulance " + ambulance.getVehicleNumber() +
                            " is not available (current status: " + ambulance.getStatus() + ")");
        }

        User dispatcher = userRepository.findById(dispatcherId)
                .orElseThrow(() -> new ResourceNotFoundException("Dispatcher", dispatcherId));

        ambulance.setStatus(Ambulance.Status.DISPATCHED);
        ambulanceRepository.save(ambulance);

        emergency.setStatus(Emergency.Status.DISPATCHED);
        emergency.setDispatcher(dispatcher);
        emergency.setAmbulance(ambulance);
        emergency.setDispatchedAt(LocalDateTime.now());

        return emergencyRepository.save(emergency);
    }

    public List<Emergency> getReportedEmergencies() {
        return emergencyRepository.findByStatusOrderByCreatedAtDesc(Emergency.Status.REPORTED);
    }

    public List<Emergency> getDispatchedEmergencies() {
        return emergencyRepository.findByStatus(Emergency.Status.DISPATCHED);
    }

    public Emergency getById(Long id) {
        return emergencyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Emergency", id));
    }

    public List<Emergency> getMyCases(Long citizenId) {
        return emergencyRepository.findByCitizenUserId(citizenId);
    }
}
