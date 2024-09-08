package com.at.spring.annotation.test;

import org.springframework.stereotype.Component;

@Component
public class Dog {

    public Dog() {
        System.out.println("this is Dog class");
    }
}
