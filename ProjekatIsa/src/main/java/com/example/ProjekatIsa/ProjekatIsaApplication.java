package com.example.ProjekatIsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ProjekatIsaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjekatIsaApplication.class, args);
		System.out.println("helloooooooooooo");
	}
}
