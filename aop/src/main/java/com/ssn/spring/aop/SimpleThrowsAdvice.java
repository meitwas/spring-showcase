package com.ssn.spring.aop;

import org.springframework.aop.ThrowsAdvice;

public class SimpleThrowsAdvice {

    public void throwException() throws Exception {
        throw new Exception();
    }

    public void throwIllegalArgumentException() throws IllegalAccessException {
        throw new IllegalArgumentException();
    }

    public static class ThrowsAdviceImpl implements ThrowsAdvice {

        public void afterThrowing(Exception ex) throws Throwable {
            System.out.println("Exception");
        }

        public void afterThrowing(IllegalArgumentException ex) throws Throwable {
            System.out.println("IllegalArgumentException");
        }

    }
}
