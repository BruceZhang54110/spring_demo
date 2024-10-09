package com.at.spring.annotation.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(value = Bug.class)
@Configuration
public class BugConfig {
}
