package com.codecool.neighbrotaxi;

import com.codecool.neighbrotaxi.model.Role;
import com.codecool.neighbrotaxi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class NeighBroTaxiApplication {
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(NeighBroTaxiApplication.class, args);
	}

	@PostConstruct
	public void fillUpDb(){
		Role role = new Role();

		if (roleRepository.findByName("ADMIN") == null) {
			role.setName("ADMIN");
			roleRepository.save(role);
		}

		if (roleRepository.findByName("USER") == null) {
			role = new Role();
			role.setName("USER");
			roleRepository.save(role);
		}
	}
}
