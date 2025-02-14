package com.eldar.products_service.configs;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class SwaggerConfig {

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("products-service")
                .packagesToScan("com.eldar.products_service")
                .build();
    }

    @Bean
    public OpenAPI springOpenApi() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearer-jwt",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("Bearer").bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("bearer-jwt"))
                .info(new Info().title("Eldar Spring Boot Career Plan Project")
                        .contact(new Contact().name("Matias Omar Batista").email("matias.batista@eldars.com.ar"))
                        .version("1.0")
                        .description("Para autenticarse con GitHub, haga clic en el siguiente enlace: [Iniciar sesión con GitHub](http://localhost:8080/oauth2/authorization/github)"));
    }

}

