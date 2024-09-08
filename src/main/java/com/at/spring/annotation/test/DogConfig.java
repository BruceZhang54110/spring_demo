package com.at.spring.annotation.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(Dog.class)
public class DogConfig {

    public DogConfig() {
        System.out.println("this is DogConfig class");
    }


}
