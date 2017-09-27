package com.ssn.spring.aop.aspectj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

    @Autowired
    private MyDependency myDependency;

    public void execute() {
        myDependency.foo(100);
    }

}
