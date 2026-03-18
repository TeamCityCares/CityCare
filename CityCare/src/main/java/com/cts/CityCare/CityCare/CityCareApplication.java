package com.cts.CityCare.CityCare;


import com.cts.CityCare.CityCare.dto.request.DispatchRequest;
import com.cts.CityCare.CityCare.entity.Ambulance;
import com.cts.CityCare.CityCare.entity.Emergency;
import com.cts.CityCare.CityCare.service.AdminService;
import com.cts.CityCare.CityCare.service.EmergencyService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication

public class CityCareApplication implements CommandLineRunner {
	@Autowired
	private EmergencyService emergencyService;

	public static void main(String[] args) {
		SpringApplication.run(CityCareApplication.class, args);
		System.out.println("CityCareApplication started");
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		//Available Ambulance
		System.out.println("Available Ambulances");
		List<Ambulance> ambulanceList = emergencyService.getAvailableAmbulances();
		ambulanceList.forEach(ambulance -> {
			System.out.println(ambulance.getModel());
		});

		/*System.out.println("Ambulance Dispatch");
		DispatchRequest request = new DispatchRequest();
		request.setAmbulanceId(104L);

		Emergency emergencyDis = emergencyService.dispatchAmbulance(2L,2L, request);
		System.out.println(emergencyDis.getStatus());
		System.out.println(emergencyDis.getDispatchedAt());
		System.out.println(emergencyDis.getLocation());*/

		/*System.out.println("Reported Emergency");
		List<Emergency> reportedEmergencyy = emergencyService.getReportedEmergencies();
		reportedEmergencyy.forEach(emergency -> {
			System.out.println(emergency.getLocation());
		});*/

		/*System.out.println("Dispatched Emergencies");
		List<Emergency> dispatchedList = emergencyService.getDispatchedEmergencies();
		dispatchedList.forEach(e -> {
			System.out.println("Location: " + e.getLocation() + " | Status: " + e.getStatus());
		});*/

		/*//	1. Fetching a Specific Emergency by ID
		System.out.println("Emergency Detail by ID ");
		Emergency singleEmergency = emergencyService.getById(2L);
		System.out.println("Location " + singleEmergency.getLocation());
		System.out.println("Status"+singleEmergency.getStatus());*/

		/*//  2. Fetching all Dispatched Emergencies
		System.out.println("All Dispatched Emergencies");
		List<Emergency> dispatchedEmergencies = emergencyService.getDispatchedEmergencies();
		dispatchedEmergencies.forEach(e -> {
			System.out.println("ID: " + e.getEmergencyId() + " | Status: " + e.getStatus());
		});*/

		// Fetching Cases for a Specific Citizen
		System.out.println("Emergencies Reported by Citizen ID ");
		List<Emergency> myCases = emergencyService.getMyCases(1L);

		if (myCases.isEmpty()) {
			System.out.println("No cases found for this citizen.");
		} else {
			myCases.forEach(emergency -> {
				System.out.println("Emergency ID: " + emergency.getEmergencyId() +
						" | Location: " + emergency.getLocation() +
						" | Status: " + emergency.getStatus());
			});
		}

	}
}