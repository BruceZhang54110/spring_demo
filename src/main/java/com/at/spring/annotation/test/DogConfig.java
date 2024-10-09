package com.at.spring.annotation.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
public class DogConfig {

    public DogConfig() {
        System.out.println("this is DogConfig class");
    }


    @Bean
    public Dog dog() {
        return new Dog();
    }
}
