package com.at.spring.annotation.test;

import org.springframework.context.annotation.Bean;

public class CatConfig {

    public CatConfig() {
        System.out.println("this is CatConfig class");
    }

    @Bean
    Cat cat() {
        return new Cat();
    }
}
