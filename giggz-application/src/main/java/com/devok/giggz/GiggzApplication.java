package com.devok.giggz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@ConfigurationPropertiesScan
@EnableEncryptableProperties
@EnableScheduling
@SpringBootApplication
public class GiggzApplication {
    public static void main(String[] args) {
        SpringApplication.run(GiggzApplication.class, args);
    }
}
