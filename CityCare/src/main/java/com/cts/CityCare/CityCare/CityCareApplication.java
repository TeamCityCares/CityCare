package com.cts.CityCare.CityCare;

import com.cts.CityCare.CityCare.entity.Ambulance;
import com.cts.CityCare.CityCare.entity.Emergency;
import com.cts.CityCare.CityCare.service.AdminService;
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
		List<Emergency> emergencies=emergencyService.getDispatchedEmergencies();
		List<Ambulance> ambulanceList = adminService.getAllAmbulances();
		ambulanceList.forEach(ambulance -> System.out.println(ambulance));
	}
}