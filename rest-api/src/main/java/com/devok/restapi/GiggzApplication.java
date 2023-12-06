package com.devok.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableEncryptableProperties
public class GiggzApplication {
    public static void main(String[] args) {
        SpringApplication.run(GiggzApplication.class, args);
    }
}
