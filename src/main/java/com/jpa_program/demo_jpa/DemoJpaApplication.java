package com.jpa_program.demo_jpa;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.jpa_program.demo_jpa.entity.Patient;
import com.jpa_program.demo_jpa.repository.PatientRepository;

@SpringBootApplication
public class DemoJpaApplication implements CommandLineRunner {

	@Autowired
	private PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		patientRepository.save(new Patient(null, "Essola", new Date(), 2300, false));
		patientRepository.save(new Patient(null, "Nouma", new Date(), 2360, false));
		patientRepository.save(new Patient(null, "Ngamesi", new Date(), 2309, false));
		patientRepository.save(new Patient(null, "Bella", new Date(), 2330, false));
		patientRepository.save(new Patient(null, "Eloun", new Date(), 2400, true));
		System.out.println("*************************");
		patientRepository.findAll().forEach(p -> {
			System.out.println(p.toString());
		});
		System.out.println("***********************");

		Patient patient = patientRepository.findById(4L).get();
		System.out.println(patient.getNom());

		System.out.println("**************************");

		Page<Patient> patients = patientRepository.findByNomContains("a", PageRequest.of(0, 2));
		patients.getContent().forEach(p -> {
			System.out.println(p.toString());
		});

		System.out.println("******************");

		List<Patient> patients2 = patientRepository.findByMalade(true);
		patients2.forEach(p -> {
			System.out.println(p.toString());
		});

		System.out.println("*******************************");

		List<Patient> patients3 = patientRepository.findByNomContainsAndMalade("o", false);
		patients3.forEach(p -> {
			System.out.println(p.toString());
		});

		patientRepository.deleteById(5L);

		System.out.println("*******************************");

		// Pagination
		Page<Patient> pagePatients = patientRepository.findAll(PageRequest.of(0, 2));
		System.out.println("Nombre de pages :" + pagePatients.getTotalPages());
		List<Patient> listPatients = pagePatients.getContent();
		listPatients.forEach(p -> {
			System.out.println(p.toString());
		});

	}

}
