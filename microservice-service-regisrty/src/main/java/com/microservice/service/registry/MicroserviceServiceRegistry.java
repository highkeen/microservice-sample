package com.microservice.service.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication(scanBasePackages = {"com.micoservice"})
@EnableEurekaServer
public class MicroserviceServiceRegistry {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceServiceRegistry.class, args);

	}

}
