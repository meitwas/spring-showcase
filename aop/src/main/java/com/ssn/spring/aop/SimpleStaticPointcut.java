package com.ssn.spring.aop;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

public class SimpleStaticPointcut extends StaticMethodMatcherPointcut {

    String methodName;

    public SimpleStaticPointcut(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return method.getName().equals(methodName);
    }

}
