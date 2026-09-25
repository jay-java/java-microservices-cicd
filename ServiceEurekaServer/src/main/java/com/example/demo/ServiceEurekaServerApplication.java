package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceEurekaServerApplication.class, args);
	}

}
