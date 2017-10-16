package jpa;

import com.ssn.spring.jpa.entity.Contact;
import com.ssn.spring.jpa.service.ContactService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

public class SpringJPATest {

    private static AbstractApplicationContext context;

    @BeforeAll
    public static void init() {
        context = new ClassPathXmlApplicationContext("META-INF/application-config.xml");
//        context = new ClassPathXmlApplicationContext("META-INF/spring/application-config-db.xml");
    }

    @Test
    @DisplayName("Test DB")
    void test1() {
        ContactService contactService = context.getBean("jpaContactService", ContactService.class);
        List<Contact> contacts = contactService.findAll();

        Assertions.assertEquals(3, contacts.size());
    }

    @Test
    @DisplayName("Test Criteria")
    void test2() {
        ContactService contactService = context.getBean("jpaContactService", ContactService.class);
        List<Contact> contacts = contactService.findByCriteriaQuery("Scott", null);

        System.out.println(contacts);
    }

    @Test
    @DisplayName("Test Save")
    void test3() {
        ContactService contactService = context.getBean("jpaContactService", ContactService.class);
        Contact contact = new Contact();
        contact.setLastName("123");
        contact.setFirstName("123");
        contact.setBirthDate(new Date());

        contactService.save(contact);

        List<Contact> contacts = contactService.findAll();
        contacts.forEach(System.out::println);

    }

    @Test
    @DisplayName("Test @Version")
    void test4() {
        ContactService contactService = context.getBean("jpaContactService", ContactService.class);

        Contact contact = contactService.findById(1l);
        contact.setLastName("1223");

//       try {
//            Thread.sleep(30000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        contactService.save(contact);


    }

}
