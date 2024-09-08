package com.at.spring.annotation.config;

import com.at.spring.annotation.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfig {

    // 给容器注册一个bean, id是默认方法名
    @Bean
    public Person person() {
        return new Person("lisi", 20);
    }
}
