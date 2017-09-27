package com.ssn.spring.aop;

import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;

public class SimpleDynamicMethodMatcherPointcut extends DynamicMethodMatcherPointcut {

    String methodName;

    public SimpleDynamicMethodMatcherPointcut(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        return method.getName().equals(methodName) && args[0].equals("Cassandra");
    }

}
