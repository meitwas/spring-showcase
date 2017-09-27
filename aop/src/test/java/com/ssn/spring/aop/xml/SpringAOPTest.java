package com.ssn.spring.aop.xml;

import com.ssn.spring.aop.*;
import com.ssn.spring.aop.aspectj.MyBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAOPTest {

    @ParameterizedTest
    @DisplayName("Test before metod advice")
    @ValueSource(strings = { "MongoDb", "Cassandra" })
    void test1(String user) {
        SimpleBeforeAdvice simpleBeforeAdvice = new SimpleBeforeAdvice();
        Assertions.assertEquals(simpleBeforeAdvice.hello(user), "Hello " + user);

        ProxyFactory proxyFactory = new ProxyFactory(simpleBeforeAdvice);
        proxyFactory.addAdvice(new SimpleBeforeAdvice.BeforeAdvice());

        SimpleBeforeAdvice proxy = (SimpleBeforeAdvice)proxyFactory.getProxy();

        Assertions.assertEquals(proxy.hello(user), "Hello " + user + "!");
    }

    @ParameterizedTest
    @DisplayName("Test after returning advice")
    @ValueSource(strings = { "MongoDb", "Cassandra" })
    void test2(String user) {
        SimpleAfterReturningAdvice simpleAfterReturningAdvice = new SimpleAfterReturningAdvice();
        Assertions.assertEquals(simpleAfterReturningAdvice.hello(user), "Hello " + user);

        ProxyFactory proxyFactory = new ProxyFactory(simpleAfterReturningAdvice);
        proxyFactory.addAdvice(new SimpleAfterReturningAdvice.AfterReturningAdvice());

        SimpleAfterReturningAdvice proxy = (SimpleAfterReturningAdvice)proxyFactory.getProxy();
        Assertions.assertNotEquals(proxy.hello(user), "Hello " + user + "!");
    }

    @Test
    @DisplayName("Test after returning advice")
    void test3() {
        SimpleMethodInterceptor simpleMethodInterceptor = new SimpleMethodInterceptor();
        simpleMethodInterceptor.work();

        ProxyFactory proxyFactory = new ProxyFactory(simpleMethodInterceptor);
        proxyFactory.addAdvice(new SimpleMethodInterceptor.MethodInterceptorImpl());

        SimpleMethodInterceptor proxy = (SimpleMethodInterceptor) proxyFactory.getProxy();
        proxy.work();
    }

    @Test
    @DisplayName("Test after returning advice")
    void test4() {
        SimpleThrowsAdvice simpleThrowsAdvice = new SimpleThrowsAdvice();
        Assertions.assertAll(() -> {
                Assertions.assertThrows(Exception.class, () -> simpleThrowsAdvice.throwException());
                Assertions.assertThrows(IllegalArgumentException.class, () -> simpleThrowsAdvice.throwIllegalArgumentException());
        });

        ProxyFactory proxyFactory = new ProxyFactory(simpleThrowsAdvice);
        proxyFactory.addAdvice(new SimpleThrowsAdvice.ThrowsAdviceImpl());

        SimpleThrowsAdvice proxy = (SimpleThrowsAdvice) proxyFactory.getProxy();
        Assertions.assertAll(() -> {
            Assertions.assertThrows(Exception.class, () -> proxy.throwException());
            Assertions.assertThrows(IllegalArgumentException.class, () -> proxy.throwIllegalArgumentException());
        });
    }

    @ParameterizedTest
    @DisplayName("Test static pointcut")
    @ValueSource(strings = { "MongoDb", "Cassandra" })
    void test5(String user) {
        SimpleBeforeAdvice simpleBeforeAdvice = new SimpleBeforeAdvice();
        Assertions.assertEquals(simpleBeforeAdvice.hello(user), "Hello " + user);

        ProxyFactory proxyFactory = new ProxyFactory(simpleBeforeAdvice);
        Advisor advisor = new DefaultPointcutAdvisor(new SimpleStaticPointcut("hello"), new SimpleBeforeAdvice.BeforeAdvice());
        proxyFactory.addAdvisor(advisor);

        SimpleBeforeAdvice proxy = (SimpleBeforeAdvice)proxyFactory.getProxy();

        Assertions.assertEquals(proxy.hello(user), "Hello " + user + "!");

        proxyFactory = new ProxyFactory(simpleBeforeAdvice);
        advisor = new DefaultPointcutAdvisor(new SimpleStaticPointcut("hi"), new SimpleBeforeAdvice.BeforeAdvice());
        proxyFactory.addAdvisor(advisor);

        proxy = (SimpleBeforeAdvice)proxyFactory.getProxy();

        Assertions.assertNotEquals(proxy.hello(user), "Hello " + user + "!");
    }

    @ParameterizedTest
    @DisplayName("Test dynamic pointcut")
    @ValueSource(strings = { "MongoDb", "Cassandra" })
    void test6(String user) {
        SimpleBeforeAdvice simpleBeforeAdvice = new SimpleBeforeAdvice();
        Assertions.assertEquals(simpleBeforeAdvice.hello(user), "Hello " + user);

        ProxyFactory proxyFactory = new ProxyFactory(simpleBeforeAdvice);
        Advisor advisor = new DefaultPointcutAdvisor(new SimpleDynamicMethodMatcherPointcut("hello"), new SimpleBeforeAdvice.BeforeAdvice());
        proxyFactory.addAdvisor(advisor);

        SimpleBeforeAdvice proxy = (SimpleBeforeAdvice)proxyFactory.getProxy();

        if (user.equals("Cassandra")) {
            Assertions.assertEquals(proxy.hello(user), "Hello " + user + "!");
        } else {
            Assertions.assertEquals(proxy.hello(user), "Hello " + user);
        }

    }

    @ParameterizedTest
    @DisplayName("Test named pointcut")
    @ValueSource(strings = { "MongoDb", "Cassandra" })
    void test7(String user) {
        SimpleBeforeAdvice simpleBeforeAdvice = new SimpleBeforeAdvice();
        Assertions.assertEquals(simpleBeforeAdvice.hello(user), "Hello " + user);

        ProxyFactory proxyFactory = new ProxyFactory(simpleBeforeAdvice);
        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
        nameMatchMethodPointcut.addMethodName("hello");
        Advisor advisor = new DefaultPointcutAdvisor(nameMatchMethodPointcut, new SimpleBeforeAdvice.BeforeAdvice());
        proxyFactory.addAdvisor(advisor);

        SimpleBeforeAdvice proxy = (SimpleBeforeAdvice)proxyFactory.getProxy();
        Assertions.assertEquals(proxy.hello(user), "Hello " + user + "!");

    }

    @ParameterizedTest
    @DisplayName("Test regexp pointcut")
    @ValueSource(strings = { "MongoDb", "Cassandra" })
    void test8(String user) {
        SimpleBeforeAdvice simpleBeforeAdvice = new SimpleBeforeAdvice();
        Assertions.assertEquals(simpleBeforeAdvice.hello(user), "Hello " + user);

        ProxyFactory proxyFactory = new ProxyFactory(simpleBeforeAdvice);
        JdkRegexpMethodPointcut jdkRegexpMethodPointcut = new JdkRegexpMethodPointcut();
        jdkRegexpMethodPointcut.setPattern(".*el*.");
        Advisor advisor = new DefaultPointcutAdvisor(jdkRegexpMethodPointcut, new SimpleBeforeAdvice.BeforeAdvice());
        proxyFactory.addAdvisor(advisor);

        SimpleBeforeAdvice proxy = (SimpleBeforeAdvice)proxyFactory.getProxy();
        Assertions.assertEquals(proxy.hello(user), "Hello " + user + "!");

    }

    @Test
    @DisplayName("Test AspectJ")
    void test9() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("com/ssn/spring/aop/application-config-annotation.xml");
        MyBean mybean = context.getBean("myBean", MyBean.class);
        mybean.execute();
    }

    @ParameterizedTest
    @DisplayName("Test before metod advice")
    @ValueSource(strings = { "MongoDb", "Cassandra" })
    void test20(String user) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("com/ssn/spring/aop/application-config.xml");
        SimpleBeforeAdvice proxy = context.getBean("simpleBeforeAdvice", SimpleBeforeAdvice.class);
        proxy.hello(user);
    }


}
