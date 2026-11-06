package org.kalinajug.projekat_rsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ServisServerApp {
    public static void main(String[] args) {
        SpringApplication.run(ServisServerApp.class, args);
    }
}