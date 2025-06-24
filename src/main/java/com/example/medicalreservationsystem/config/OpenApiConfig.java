package com.example.medicalreservationsystem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Medical Reservation System API")
                        .description("Dokumentacja REST API dla systemu rezerwacji wizyt")
                        .version("1.0.0"));
    }
}
