package com.band.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@EnableFeignClients
@EnableEurekaClient
@OpenAPIDefinition
@SpringBootApplication
public class BandServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BandServiceApplication.class, args);
	}

}
