package com.microservice.identity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication()
@EnableDiscoveryClient
public class MicroserviceIdentity {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceIdentity.class, args);

	}
}
