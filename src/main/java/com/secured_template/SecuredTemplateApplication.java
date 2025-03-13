package com.secured_template;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class SecuredTemplateApplication {
	public static void main(String[] args) {
		SpringApplication.run(SecuredTemplateApplication.class, args);
	}

}
