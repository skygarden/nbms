package com.nexblue.nbms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NbmsApplication {

	public static void main(String[] args) {
		// Dotenv dotenv = Dotenv.configure().load();
        // dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
		SpringApplication.run(NbmsApplication.class, args);
	}

}
