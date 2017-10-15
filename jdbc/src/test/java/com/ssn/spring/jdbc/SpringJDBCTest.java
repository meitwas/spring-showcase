package com.ssn.spring.jdbc;

import org.junit.jupiter.api.*;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class SpringJDBCTest {

    private static AbstractApplicationContext context;

    @BeforeAll
    public static void init() {
        context = new ClassPathXmlApplicationContext("META-INF/spring/application-config-embedded.xml");
//        context = new ClassPathXmlApplicationContext("META-INF/spring/application-config-db.xml");
    }

    @Test
    @DisplayName("Test DB")
    void test1() {
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        JdbcTemplate template = new JdbcTemplate(dataSource);

        Assertions.assertEquals("Schaefer", template.queryForObject("select last_name from contact where first_name ='Chris'", String.class));
    }

    @Test
    @DisplayName("Test NamedJdbcTemplate")
    void test2() {
        ContactDao contactDao = context.getBean("contactDao", ContactDao.class);

        String lastNameById = contactDao.findLastNameById(2l);
        Assertions.assertEquals("Tiger", lastNameById);

    }

    @Test
    @DisplayName("Test RowMaper")
    void test3() {
        ContactDao contactDao = context.getBean("contactDao", ContactDao.class);

        List<Contact> contacts = contactDao.findAll();
        Assertions.assertEquals(3, contacts.size());

    }

    @Test
    @DisplayName("Test ResultSetExtractor")
    void test4() {
        ContactDao contactDao = context.getBean("contactDao", ContactDao.class);

        List<Contact> contacts = contactDao.findAllWithDetail();
        for (Contact contact : contacts) {
            Assertions.assertEquals(1, contact.getContactTelDetails().size());
        }

    }

    @Test
    @DisplayName("Test JdbcTemplate DaoSupport")
    void test5() {
        ContactDao contactDao = context.getBean("contactDaoSupport", ContactDao.class);

        String lastNameById = contactDao.findLastNameById(2l);
        Assertions.assertEquals("Tiger", lastNameById);
    }

    @Test
    @DisplayName("Test RowMaper DaoSupport")
    void test6() {
        ContactDao contactDao = context.getBean("contactDaoSupport", ContactDao.class);

        List<Contact> contacts = contactDao.findAll();
        Assertions.assertEquals(3, contacts.size());

    }

    @Test
    @DisplayName("Test ResultSetExtractor DaoSupport")
    void test7() {
        ContactDao contactDao = context.getBean("contactDaoSupport", ContactDao.class);

        List<Contact> contacts = contactDao.findAllWithDetail();
        for (Contact contact : contacts) {
            Assertions.assertEquals(1, contact.getContactTelDetails().size());
        }
    }
}
