package com.at.spring.spring2023;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("com.at.spring.spring2023")
@Configuration
public class MainConfig {



    @Bean("person")
    Person person() {
        return new Person("ddd");
    }
}
