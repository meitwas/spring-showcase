package com.ssn.spring.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringJDBCTest {

    @Test
    void test1() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/application-config.xml");
    }
}
