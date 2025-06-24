package com.example.medicalreservationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.medicalreservationsystem")
@EntityScan("com.example.medicalreservationsystem")
@EnableJpaRepositories("com.example.medicalreservationsystem")
public class MedAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(MedAppApplication.class, args);
    }
}
