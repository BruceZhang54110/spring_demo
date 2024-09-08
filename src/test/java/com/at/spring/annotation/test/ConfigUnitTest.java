package com.at.spring.annotation.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DogConfig.class, BirdConfig.class, CatConfig.class})
public class ConfigUnitTest {

    @Autowired
    ApplicationContext context;

    @Test
    void givenImportedBeansWhenGettingEachShallFindIt() {
        assertThatBeanExists("com.at.spring.annotation.test.Dog", Dog.class);
        assertThatBeanExists("cat", Cat.class);
        assertThatBeanExists("bird", Bird.class);
    }

    private void assertThatBeanExists(String beanName, Class<?> beanClass) {
        String[] beanNamesForType = context.getBeanNamesForType(beanClass);
        System.out.println(Arrays.toString(beanNamesForType));
        Assertions.assertTrue(context.containsBean(beanName));
        Assertions.assertNotNull(context.getBean(beanClass));

    }
}
