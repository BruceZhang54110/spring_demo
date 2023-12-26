package com.at.spring.spring2023;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Dog {

    private String name;

    @Autowired
    private Person person;


    public Dog(String name) {
        System.out.println("constructor dog ,,,");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PostConstruct
    void aaa() {
        System.out.println("init dog ,,,");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
