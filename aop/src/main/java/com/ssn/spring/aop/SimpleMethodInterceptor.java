package com.ssn.spring.aop;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class SimpleMethodInterceptor {

    public void work() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished");
    }

    public static class MethodInterceptorImpl implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            long startTime = System.nanoTime();
            Object proceed = invocation.proceed();
            System.out.println("Method invocation takes: " + (System.nanoTime() - startTime) + " ns");
            return proceed;
        }
    }
}
