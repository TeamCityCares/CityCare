package com.cts.CityCare.CityCare;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication

public class CityCareApplication implements CommandLineRunner {

	@Autowired
	private EmergencyService emergencyService;

	@Autowired

	private AdminService adminService;

	public static void main(String[] args) {
		SpringApplication.run(CityCareApplication.class, args);
		System.out.println("CityCareApplication started");
	}

	@Override
	public void run(String... args) throws Exception {


//		to add the ambulance

		AmbulanceRequest reqq = new AmbulanceRequest();
		reqq.setVehicleNumber("TN-2022-002");
		reqq.setModel("Maruti Suzuki");





		// To get the all the methods
		List<Ambulance> ambulanceList = adminService.getAllAmbulances();
		ambulanceList.forEach(ambulance -> {
			System.out.println(ambulance.getAmbulanceId());
			System.out.println(ambulance.getCreatedAt());
			System.out.println(ambulance.getModel());
			System.out.println(ambulance.getStatus());
			System.out.println(ambulance.getVehicleNumber());
		});




	}
}
