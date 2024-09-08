package com.at.spring.annotation.test;

import org.springframework.context.annotation.Bean;

public class BirdConfig {
    public BirdConfig() {
        System.out.println("this is BirdConfig class");
    }

    @Bean
    Bird bird() {
        return new Bird();
    }
}
