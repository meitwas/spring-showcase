package com.ssn.spring.aop;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class SimpleAfterReturningAdvice {

    public String hello(String userName) {
        return "Hello " + userName;
    }

    public static class AfterReturningAdvice implements org.springframework.aop.AfterReturningAdvice {

        @Override
        public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
                System.out.println("after returning advice");
                returnValue = returnValue + "!";
        }
    }

}
