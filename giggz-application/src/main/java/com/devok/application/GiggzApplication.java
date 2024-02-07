package com.devok.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@ConfigurationPropertiesScan
@EnableEncryptableProperties
@SpringBootApplication
@ComponentScan(basePackages = {"com.devok.restapi.invoker"})
public class GiggzApplication {
    public static void main(String[] args) {
        SpringApplication.run(GiggzApplication.class, args);
    }
}
