package com.at.spring.annotation.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({MammalConfiguration.class, BirdConfig.class, BugConfig.class})
@Configuration
public class AnimalConfiguration {


}
