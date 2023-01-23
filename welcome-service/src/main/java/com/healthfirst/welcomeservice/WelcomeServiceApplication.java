package com.healthfirst.welcomeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class WelcomeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WelcomeServiceApplication.class, args);
	}

}
