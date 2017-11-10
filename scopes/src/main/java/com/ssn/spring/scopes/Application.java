package com.ssn.spring.scopes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application {

    @Autowired
    @Qualifier("testBeanRequest")
    private TestBean testBeanRequest;

    @Autowired
    @Qualifier("testBeanSession")
    private TestBean testBeanSession;

    @Autowired
    @Qualifier("testBeanSession")
    private TestBean testBeanSingleton1;

    @Autowired
    @Qualifier("testBeanSession")
    private TestBean testBeanSingleton2;

    @Autowired
    @Qualifier("testBeanPrototype")
    private TestBean testBeanPrototype1;

    @Autowired
    @Qualifier("testBeanPrototype")
    private TestBean testBeanPrototype2;

    @RequestMapping("/request")
    public String request() {
        System.out.println("Test Request Class" + testBeanRequest.getClass());
        System.out.println("Test Request " + testBeanRequest.hashCode());
        System.out.println("Test Request Object " + testBeanRequest.getObj().hashCode());
        System.out.println();
        return "Ok";
    }

    @RequestMapping("/session")
    public String session() {
        System.out.println("Test Session Class" + testBeanSession.getClass());
        System.out.println("Test Session " + testBeanSession.hashCode());
        System.out.println("Test Session Object " + testBeanSession.getObj().hashCode());
        System.out.println();
        return "Ok";
    }

    @RequestMapping("/singleton")
    public String singleton() {
        System.out.println("Test Singleton Class" + testBeanSingleton1.getClass());
        System.out.println("Test Singleton " + testBeanSingleton1.hashCode());
        System.out.println("Test Singleton Object " + testBeanSingleton1.getObj().hashCode());
        System.out.println();

        System.out.println("Test Singleton Class" + testBeanSingleton2.getClass());
        System.out.println("Test Singleton " + testBeanSingleton2.hashCode());
        System.out.println("Test Singleton Object " + testBeanSingleton2.getObj().hashCode());
        System.out.println();
        return "Ok";
    }

    @RequestMapping("/prototype")
    public String prototype() {
        System.out.println("Test Prototype Class" + testBeanPrototype1.getClass());
        System.out.println("Test Prototype " + testBeanPrototype1.hashCode());
        System.out.println("Test Prototype Object " + testBeanPrototype1.getObj().hashCode());
        System.out.println();

        System.out.println("Test Prototype Class" + testBeanPrototype2.getClass());
        System.out.println("Test Prototype " + testBeanPrototype2.hashCode());
        System.out.println("Test Prototype Object " + testBeanPrototype2.getObj().hashCode());
        System.out.println();
        return "Ok";
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public TestBean testBeanRequest() {
        return new TestBean();
    }

    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public TestBean testBeanSession() {
        return new TestBean();
    }

    @Bean
    @Scope("singleton")
    public TestBean testBeanSingleton() {
        return new TestBean();
    }

    @Bean
    @Scope("prototype")
    public TestBean testBeanPrototype() {
        return new TestBean();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
