package com.devok.restapi.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class MyComponent {

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void checkIfBeanExists() {
        System.out.println("Checking for bean existence...");
        if (applicationContext != null) {
            boolean beanExists = applicationContext.containsBean("yourBeanName");
            if (beanExists) {
                System.out.println("The bean exists!");
            } else {
                System.out.println("The bean does not exist!");
            }
        } else {
            System.out.println("Application context is null!");
        }
    }
}