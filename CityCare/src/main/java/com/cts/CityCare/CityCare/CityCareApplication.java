package com.cts.CityCare.CityCare;

<<<<<<< HEAD

import com.cts.CityCare.CityCare.dto.request.AmbulanceRequest;
import com.cts.CityCare.CityCare.entity.Ambulance;
import com.cts.CityCare.CityCare.service.AdminService;

import com.cts.CityCare.CityCare.entity.Ambulance;
import com.cts.CityCare.CityCare.entity.Emergency;
import com.cts.CityCare.CityCare.service.AdminService;
import com.cts.CityCare.CityCare.service.EmergencyService;

=======
import com.cts.CityCare.CityCare.dto.request.AdmitPatientRequest;
import com.cts.CityCare.CityCare.entity.Patient;
import com.cts.CityCare.CityCare.entity.User;
import com.cts.CityCare.CityCare.service.PatientService;
>>>>>>> 262735c36309d3b320650c608f9037622ab71601
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
<<<<<<< HEAD
=======
import java.util.Optional;
>>>>>>> 262735c36309d3b320650c608f9037622ab71601

@SpringBootApplication

public class CityCareApplication implements CommandLineRunner {
<<<<<<< HEAD

	@Autowired
	private EmergencyService emergencyService;

	@Autowired

	private AdminService adminService;
=======
	@Autowired
	private PatientService patientService;
>>>>>>> 262735c36309d3b320650c608f9037622ab71601

	public static void main(String[] args) {
		SpringApplication.run(CityCareApplication.class, args);

	}
<<<<<<< HEAD

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
=======
	@Override
	public void run(String... args) throws Exception {
//		System.out.println("--- STARTING SERVICE TESTS ---");
//
//		try {
//
//
//
////			List<Patient> patientList = patientService.getAll();
////			System.out.println("********************---- Details of Patient : ---*********************");
////			patientList.forEach(patient -> {
////				System.out.println("Notes : "+patient.getNotes());
////				System.out.println(patient.getPatientId());
////				System.out.println(patient.getAdmissionDate());
////				System.out.println(patient.getStatus());
////				System.out.println(patient.getWard());
////			});
////
////			Patient patient1 = patientService.updateStatus(1L,Patient.Status.STABLE);
////			System.out.println(patient1.getStatus());
//
//
//			Patient patient2 = patientService.getById(1L);
//			System.out.println(patient2);
//
//			List<Patient> oyy = patientService.getByStatus(Patient.Status.STABLE);
//			for(int i=0;i<oyy.size();i++){
//				System.out.println(oyy.get(i));
//			}
//
//
//
//
////			System.out.println("********************---- Details of Patient : ---*********************");
////			patientList.forEach(patient -> {
////				System.out.println("Notes : "+patient.getNotes());
////				System.out.println(patient.getPatientId());
////				System.out.println(patient.getAdmissionDate());
////				System.out.println(patient.getStatus());
////				System.out.println(patient.getWard());
////			});
//
//
//
////			// 2. Test updateStatus (Discharging the patient we just created)
////			Patient discharged = patientService.updateStatus(admitted.getPatientId(), Patient.Status.DISCHARGED);
////			System.out.println("Discharged Status: " + discharged.getStatus() + " Date: " + discharged.getDischargeDate());
////
////			// 3. Test getById
////			Patient found = patientService.getById(admitted.getPatientId());
////			System.out.println("Found Patient by ID: " + found.getPatientId());
//
////			// 4. Test getAll
////			System.out.println("Total Patients in System: " + patientService.getAll().size());
//
//		} catch (Exception e) {
//			System.err.println("Test Failed: " + e.getMessage());
//		}
	}

}
>>>>>>> 262735c36309d3b320650c608f9037622ab71601
