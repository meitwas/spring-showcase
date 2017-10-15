package com.ssn.spring.jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class SpringJDBCTest {

    @Test
    @DisplayName("Test Embedded DB")
    void test1() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/application-config-embedded.xml");
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        JdbcTemplate template = new JdbcTemplate(dataSource);

        Assertions.assertEquals("Schaefer", template.queryForObject("select last_name from contact where first_name ='Chris'", String.class));
    }

    @Test
    @DisplayName("Test DB")
    void test2() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/application-config-db.xml");
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        JdbcTemplate template = new JdbcTemplate(dataSource);

        Assertions.assertEquals("Schaefer", template.queryForObject("select last_name from contact where first_name ='Chris'", String.class));
    }
}
