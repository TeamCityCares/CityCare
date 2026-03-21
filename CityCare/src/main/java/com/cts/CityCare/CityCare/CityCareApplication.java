package com.cts.CityCare.CityCare;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;



@SpringBootApplication
@EnableJpaAuditing
public class CityCareApplication{


	public static void main(String[] args) {
		SpringApplication.run(CityCareApplication.class, args);
		System.out.println("CityCareApplication started -- Vivek Patel");
	}

}