package com.easytobuy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker

@EnableDiscoveryClient
public class ProductApiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApiServiceApplication.class, args);
	}

}
