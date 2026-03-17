package com.cts.CityCare.CityCare;

import com.cts.CityCare.CityCare.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CityCareApplication {
	@Autowired
	private FacilityService facilityService;


	public static void main(String[] args) {
		SpringApplication.run(CityCareApplication.class, args);
		System.out.println("Application Successfully Running");

	}
}


