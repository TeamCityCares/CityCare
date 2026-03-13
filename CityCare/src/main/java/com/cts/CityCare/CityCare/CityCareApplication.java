package com.cts.CityCare.CityCare;


import com.cts.CityCare.CityCare.dto.request.AmbulanceRequest;
import com.cts.CityCare.CityCare.entity.Ambulance;
import com.cts.CityCare.CityCare.service.AdminService;

import com.cts.CityCare.CityCare.entity.Ambulance;
import com.cts.CityCare.CityCare.entity.Emergency;
import com.cts.CityCare.CityCare.service.AdminService;
import com.cts.CityCare.CityCare.service.EmergencyService;

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


//package com.cts.CityCare.CityCare;
//
//import com.cts.CityCare.CityCare.dto.request.AmbulanceRequest;
//import com.cts.CityCare.CityCare.dto.request.CreateStaffRequest;
//import com.cts.CityCare.CityCare.entity.User;
//import com.cts.CityCare.CityCare.service.AdminService;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//
//@SpringBootApplication
//@EnableJpaAuditing
//public class CityCareApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(CityCareApplication.class, args);
//		System.out.println("CityCareApplication started");
//	}
//
//	@Bean
//	public CommandLineRunner testAdminService(AdminService adminService) {
//		return args -> {
//			System.out.println("\n--- RUNNING SERVICE LAYER TESTS ---");
//
//			try {
//				// 1. Test Adding an Ambulance
//				AmbulanceRequest ambReq = new AmbulanceRequest();
//				ambReq.setVehicleNumber("AMB-2024-001");
//				ambReq.setModel("Force Traveller");
//
//				var savedAmb = adminService.addAmbulance(ambReq);
//				System.out.println("SUCCESS: Ambulance created with ID: " + savedAmb.getAmbulanceId());
//
//				// 2. Test Creating a Staff Member (Doctor)
//				CreateStaffRequest staffReq = new CreateStaffRequest();
//				staffReq.setName("Dr. Smith");
//				staffReq.setEmail("smith@citycare.com");
//				staffReq.setPassword("securePass123");
//				staffReq.setRole(User.Role.DOCTOR);
//				staffReq.setPhone("9876543210");
//
//				var savedStaff = adminService.createStaff(staffReq);
//				System.out.println("SUCCESS: Staff created with Role: " + savedStaff.getRole());
//
//				// 3. Test Retrieval
//				int userCount = adminService.getAllUsers().size();
//				System.out.println("TOTAL USERS IN DB: " + userCount);
//
//			} catch (Exception e) {
//				System.err.println("TEST FAILED: " + e.getMessage());
//				// This will catch your BadRequestExceptions (e.g., duplicate email)
//			}
//
//			System.out.println("--- SERVICE TESTS COMPLETE ---\n");
//		};
//	}
//}
=======
		List<Emergency> emergencies=emergencyService.getDispatchedEmergencies();
		List<Ambulance> ambulanceList = adminService.getAllAmbulances();
		ambulanceList.forEach(ambulance -> System.out.println(ambulance));
	}
}
>>>>>>> 9a9fa07d8090f7b2e79f711d31a33ef56485dca0
