package com.nguyen.microserices.composite.ekart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.nguyen")
@SpringBootApplication
public class CompositeServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CompositeServiceApplication.class);
    }
}
