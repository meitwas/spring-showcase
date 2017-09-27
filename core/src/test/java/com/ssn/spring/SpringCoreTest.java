package com.ssn.spring;

import com.ssn.spring.annotation.Config;
import com.ssn.spring.annotation.Defender;
import com.ssn.spring.annotation.Worker;
import com.ssn.spring.xml.BartSimpson;
import com.ssn.spring.xml.HomerSimpson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

public class SpringCoreTest {

    @Test
    @DisplayName("Get bean from context")
    void test1() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/application-xml.xml");

        HomerSimpson homer = context.getBean("homer", HomerSimpson.class);
        homer.say();
    }


    @Test
    @DisplayName("Get Bean from factory-method")
    void test2() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/application-xml.xml");

        HomerSimpson homerFromFactory = context.getBean("homerFromFactory", HomerSimpson.class);
        homerFromFactory.say();

    }

    @Test
    @DisplayName("Test singleton")
    void test3() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/application-xml.xml");
        HomerSimpson homer1 = context.getBean("homer", HomerSimpson.class);
        HomerSimpson homer2 = context.getBean("homer", HomerSimpson.class);

        Assertions.assertEquals(homer1, homer2);

    }

    @Test
    @DisplayName("Get Bean from factory-bean")
    void test4() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/application-xml.xml");
        BartSimpson bart = context.getBean("bartSimpsonFromFather", BartSimpson.class);

        bart.say();

    }

    @Test
    @DisplayName("Property set")
    void test5() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/application-xml.xml");
        HomerSimpson homer = context.getBean("homerClone1", HomerSimpson.class);

        Assertions.assertEquals(homer.getBeerAmount(), new Long(15));

    }
    @Test
    @DisplayName("property set via 'p'")
    void test6() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/application-xml.xml");
        HomerSimpson homer = context.getBean("homerClone2", HomerSimpson.class);

        Assertions.assertEquals(homer.getBeerAmount(), new Long(13));

    }

    @Test
    @DisplayName("property set via 'c'")
    void test7() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/application-xml.xml");
        HomerSimpson homer = context.getBean("homerClone3", HomerSimpson.class);

        Assertions.assertEquals(homer.getBeerAmount(), new Long(112));

    }

    @Test
    @DisplayName("Create library class")
    void test8() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/application-xml.xml");
        HomerSimpson homer = context.getBean("homerClone4", HomerSimpson.class);

        Assertions.assertEquals(homer.getBeerAmount(), new Long(34));

    }
    @Test
    @DisplayName("Test Collections")
    void test9() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/application-xml.xml");
        HomerSimpson homer = context.getBean("homerClone5", HomerSimpson.class);

        System.out.println(homer.getChildren());

        Assertions.assertEquals(homer.getChildren().size(), 3);

    }

    @Test
    @DisplayName("Test Collections wit properties")
    void test10() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/application-xml.xml");
        HomerSimpson homer = context.getBean("homerClone6", HomerSimpson.class);

        System.out.println(homer.getChildren());


    }

    @Test
    @DisplayName("Test i18n")
    void test11() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/application-xml.xml");
        System.out.println(context.getMessage("child1", null, Locale.ENGLISH));
        System.out.println(context.getMessage("child2", null, Locale.ENGLISH));
        System.out.println(context.getMessage("child3", null, Locale.ENGLISH));

        System.out.println(context.getMessage("child1", null, new Locale("ru", "RU")));
        System.out.println(context.getMessage("child2", null, new Locale("ru", "RU")));
        System.out.println(context.getMessage("child3", null, new Locale("ru", "RU")));

    }

    @Test
    void test12() {

//        System.setProperty("spring.profiles.active", "starwars");


        AbstractApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/application-annotation.xml");

        Worker worker = context.getBean("worker", Worker.class);

        worker.work();

    }

    @Test
    @DisplayName("Configurarion via JAVA")
    void test13() {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        Defender defender = context.getBean("defender", Defender.class);

        defender.protect();
    }

    @Test
    @DisplayName("LifeCycle")
    void test14() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/application-lifecycle.xml");
        context.close();
    }

    @Test
    @DisplayName("Messages")
    void test15() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/application-messages.xml");
        context.close();
    }

}
