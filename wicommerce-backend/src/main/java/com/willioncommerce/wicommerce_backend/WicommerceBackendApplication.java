package com.willioncommerce.wicommerce_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class WicommerceBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(WicommerceBackendApplication.class, args);
	}

}
