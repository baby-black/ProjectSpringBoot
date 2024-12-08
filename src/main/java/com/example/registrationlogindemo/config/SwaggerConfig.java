package com.example.registrationlogindemo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API Documentation",
                description = "API documentation for the registration and login system",
                version = "1.0.0",
                contact = @Contact(name = "Developer", email = "developer@example.com")
        )
)
public class SwaggerConfig {

    // Springdoc OpenAPI tự động quét tất cả các endpoint mà không cần cấu hình Docket như trong Swagger 2
}
