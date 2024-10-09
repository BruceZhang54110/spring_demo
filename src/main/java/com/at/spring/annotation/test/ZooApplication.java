package com.at.spring.annotation.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(value = AnimalScanConfiguration.class)
@Configuration
public class ZooApplication {
}
