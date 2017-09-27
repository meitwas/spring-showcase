package com.ssn.spring.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

public class SimpleBeforeAdvice {

    public String hello(String user) {
        return "Hello " + user;
    }

    public static class BeforeAdvice implements MethodBeforeAdvice {

        @Override
        public void before(Method method, Object[] args, Object target) throws Throwable {
            for (int i = 0; i < args.length; i++) {
                args[i] = args[i] + "!";
            }
        }

    }

}
