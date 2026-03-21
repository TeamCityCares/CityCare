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
	// @Autowired
	// private EmergencyService emergencyService;

	public static void main(String[] args) {
		SpringApplication.run(CityCareApplication.class, args);
		System.out.println("CityCareApplication started");
	}

	@Override
	public void run(String... args) throws Exception {

	}

	// @Override
	// @Transactional
	// public void run(String... args) throws Exception {
	// System.out.println("--- STARTING SERVICE TESTS ---");
	//
	// try {
	//
	//
	//
	//// List<Patient> patientList = patientService.getAll();
	//// System.out.println("********************---- Details of Patient :
	// ---*********************");
	//// patientList.forEach(patient -> {
	//// System.out.println("Notes : "+patient.getNotes());
	//// System.out.println(patient.getPatientId());
	//// System.out.println(patient.getAdmissionDate());
	//// System.out.println(patient.getStatus());
	//// System.out.println(patient.getWard());
	//// });
	////
	/// Patient patient1 = patientService.updateStatus(1L,Patient.Status.STABLE);
	//// System.out.println(patient1.getStatus());
	//
	//
	// Patient patient2 = patientService.getById(1L);
	// System.out.println(patient2);
	//
	// List<Patient> oyy = patientService.getByStatus(Patient.Status.STABLE);
	// for(int i=0;i<oyy.size();i++){
	// System.out.println(oyy.get(i));
	// }
	//
	//
	//
	//
	//// System.out.println("********************---- Details of Patient :
	// ---*********************");
	//// patientList.forEach(patient -> {
	//// System.out.println("Notes : "+patient.getNotes());
	//// System.out.println(patient.getPatientId());
	//// System.out.println(patient.getAdmissionDate());
	//// System.out.println(patient.getStatus());
	//// System.out.println(patient.getWard());
	//// });
	//
	//
	//
	//// // 2. Test updateStatus (Discharging the patient we just created)
	//// Patient discharged = patientService.updateStatus(admitted.getPatientId(),
	// Patient.Status.DISCHARGED);
	//// System.out.println("Discharged Status: " + discharged.getStatus() + " Date:
	// " + discharged.getDischargeDate());
	////
	//// // 3. Test getById
	//// Patient found = patientService.getById(admitted.getPatientId());
	//// System.out.println("Found Patient by ID: " + found.getPatientId());
	//
	//// // 4. Test getAll
	//// System.out.println("Total Patients in System: " +
	// patientService.getAll().size());
	//
	// } catch (Exception e) {
	// System.err.println("Test Failed: " + e.getMessage());
	// }
	// }

	// Emergency emergencyDis = emergencyService.dispatchAmbulance(2L,2L, request);
	// System.out.println(emergencyDis.getStatus());
	// System.out.println(emergencyDis.getDispatchedAt());
	// System.out.println(emergencyDis.getLocation());*/

	/*
	 * System.out.println("Reported Emergency");
	 * List<Emergency> reportedEmergencyy =
	 * emergencyService.getReportedEmergencies();
	 * reportedEmergencyy.forEach(emergency -> {
	 * System.out.println(emergency.getLocation());
	 * });
	 */

	/*
	 * System.out.println("Dispatched Emergencies");
	 * List<Emergency> dispatchedList = emergencyService.getDispatchedEmergencies();
	 * dispatchedList.forEach(e -> {
	 * System.out.println("Location: " + e.getLocation() + " | Status: " +
	 * e.getStatus());
	 * });
	 */

	/*
	 * // 1. Fetching a Specific Emergency by ID
	 * System.out.println("Emergency Detail by ID ");
	 * Emergency singleEmergency = emergencyService.getById(2L);
	 * System.out.println("Location " + singleEmergency.getLocation());
	 * System.out.println("Status"+singleEmergency.getStatus());
	 */

	/*
	 * // 2. Fetching all Dispatched Emergencies
	 * System.out.println("All Dispatched Emergencies");
	 * List<Emergency> dispatchedEmergencies =
	 * emergencyService.getDispatchedEmergencies();
	 * dispatchedEmergencies.forEach(e -> {
	 * System.out.println("ID: " + e.getEmergencyId() + " | Status: " +
	 * e.getStatus());
	 * });
	 */

	// Fetching Cases for a Specific Citizen
	// System.out.println("Emergencies Reported by Citizen ID ");
	// List<Emergency> myCases = emergencyService.getMyCases(1L);

	// if (myCases.isEmpty()) {
	// System.out.println("No cases found for this citizen.");
	// } else {
	// myCases.forEach(emergency -> {
	// System.out.println("Emergency ID: " + emergency.getEmergencyId() +
	// " | Location: " + emergency.getLocation() +
	// " | Status: " + emergency.getStatus());
	// });
	// }

	// }
}