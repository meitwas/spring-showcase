package com.ssn.spring.aop.aspectj;

import org.springframework.stereotype.Component;

@Component
public class MyDependency {

    public void foo(int intValue) {
        System.out.println("foo(int): " + intValue);
    }

    public void bar() {
        System.out.println("bar()");
    }

}
