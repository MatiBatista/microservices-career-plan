package com.eldar.gateway.Swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi personServiceApi() {
        return GroupedOpenApi.builder()
                .group("person-service")// Nombre que aparecerá en Swagger UI
                .pathsToMatch("/person/**")  // Rutas relacionadas con `person-service`
                .build();
    }

    @Bean
    public GroupedOpenApi productServiceApi() {
        return GroupedOpenApi.builder()
                .group("products-service")// Nombre que aparecerá en Swagger UI
                .pathsToMatch("/products/**")  // Rutas relacionadas con `person-service`
                .build();
    }

    @Bean
    public GroupedOpenApi salesServiceApi() {
        return GroupedOpenApi.builder()
                .group("sales-service")// Nombre que aparecerá en Swagger UI
                .pathsToMatch("/sales/**")  // Rutas relacionadas con `person-service`
                .build();
    }



}
