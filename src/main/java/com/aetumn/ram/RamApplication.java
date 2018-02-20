package com.aetumn.ram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RamApplication {

	public static void main(String[] args) {
		SpringApplication.run(RamApplication.class, args);
		System.out.println("RAM application started");
	}
}
