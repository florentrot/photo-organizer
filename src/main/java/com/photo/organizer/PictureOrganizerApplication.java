package com.photo.organizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PictureOrganizerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PictureOrganizerApplication.class, args);
    }
}