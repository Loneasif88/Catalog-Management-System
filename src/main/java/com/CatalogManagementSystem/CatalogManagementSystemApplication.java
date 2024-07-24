package com.CatalogManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories("com.CatalogManagementSystem.Repository")
public class CatalogManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogManagementSystemApplication.class, args);
		System.out.println("Alhumdulillah");
	}

}
