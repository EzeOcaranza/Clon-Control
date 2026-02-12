package com.clon.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.clon", "api", "config", "ui"})
public class ClonControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClonControlApplication.class, args);
    }
}
