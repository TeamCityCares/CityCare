package com.cts.CityCare.CityCare;

import com.cts.CityCare.CityCare.dto.request.AdmitPatientRequest;
import com.cts.CityCare.CityCare.entity.Patient;
import com.cts.CityCare.CityCare.entity.User;
import com.cts.CityCare.CityCare.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication

public class CityCareApplication implements CommandLineRunner {
	@Autowired
	private PatientService patientService;

	public static void main(String[] args) {
		SpringApplication.run(CityCareApplication.class, args);

	}
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
///			Patient patient1 = patientService.updateStatus(1L,Patient.Status.STABLE);
////			System.out.println(patient1.getStatus());
//
//
		Patient patient2 = patientService.getById(1L);
			System.out.println(patient2);
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