package com.devops.productservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Product microservices REST API Documentation",
				description = "JMarket Product microservice REST API Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Johan Markets",
						email = "johan@works.com",
						url = "https://johanmarkets.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://johanmarkets.com"
				)
		)
)
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
