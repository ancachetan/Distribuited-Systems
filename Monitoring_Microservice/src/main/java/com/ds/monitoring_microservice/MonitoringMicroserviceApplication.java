package com.ds.monitoring_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MonitoringMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitoringMicroserviceApplication.class, args);
	}
}
