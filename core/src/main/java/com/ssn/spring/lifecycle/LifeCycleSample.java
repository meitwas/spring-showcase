package com.ssn.spring.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class LifeCycleSample implements InitializingBean, DisposableBean {


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("After properties set");
    }

    public void initMethod() {
        System.out.println("Init method");
    }

    @PostConstruct
    public void init() {
        System.out.println("PostConstruct");
    }





    @Override
    public void destroy() throws Exception {
        System.out.println("Destroy");
    }

    public void destroyMethod() {
        System.out.println("Destroy method");
    }

    @PreDestroy
    public void preDesrtroy() throws Exception {
        System.out.println("PreDestroy");
    }
}
