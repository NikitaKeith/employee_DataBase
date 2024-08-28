package com.andreev.employeeDB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.andreev.employeeDB")
public class EmployeeDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeDbApplication.class, args);
	}
}