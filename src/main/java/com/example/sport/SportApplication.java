package com.example.sport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SportApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportApplication.class, args);
	}
}