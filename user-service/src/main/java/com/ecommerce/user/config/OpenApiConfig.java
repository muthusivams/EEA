package com.ecommerce.user.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@OpenAPIDefinition(
    info = @Info(
        title = "User Service API",
        version = "v1",
        description = "Swagger/OpenAPI documentation for user service endpoints.",
        contact = @Contact(name = "eCommerce Platform Team", email = "platform@ecommerce.example.com")
    ),
    servers = @Server(url = "/", description = "Default server"),
    security = @SecurityRequirement(name = "bearerAuth")
)
public class OpenApiConfig {
}
