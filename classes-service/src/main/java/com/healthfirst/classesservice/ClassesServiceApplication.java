package com.healthfirst.classesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ClassesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassesServiceApplication.class, args);
	}

}
