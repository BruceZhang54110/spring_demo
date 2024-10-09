package com.at.spring.annotation.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({DogConfig.class, CatConfig.class})
@Configuration
public class MammalConfiguration {
}
