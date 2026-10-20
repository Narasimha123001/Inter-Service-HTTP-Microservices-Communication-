package com.techtricks.ecom_order_servise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EcomOrderServiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomOrderServiseApplication.class, args);
	}

}
