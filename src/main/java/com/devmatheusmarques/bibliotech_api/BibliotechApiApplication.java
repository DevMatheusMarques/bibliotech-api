package com.devmatheusmarques.bibliotech_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.devmatheusmarques.bibliotech_api")
public class BibliotechApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotechApiApplication.class, args);
	}

}
