package com.clon.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.clon",
    "api",
    "config",
    "ui",
    "Ejercicio1",
    "Ejercicio2"
})
@EntityScan(basePackages = {
    "Ejercicio2",
    "api"
})
@EnableJpaRepositories(basePackages = {
    "Ejercicio2.repository",
    "api.repository"
})
public class ClonControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClonControlApplication.class, args);
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   🚀 Clon Control - API REST Started   ║");
        System.out.println("║   http://localhost:8080/api            ║");
        System.out.println("╚════════════════════════════════════════╝");
    }
}
