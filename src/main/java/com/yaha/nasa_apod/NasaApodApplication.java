package com.yaha.nasa_apod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class NasaApodApplication {

    public static void main(String[] args) {
        SpringApplication.run(NasaApodApplication.class, args);
    }

}
