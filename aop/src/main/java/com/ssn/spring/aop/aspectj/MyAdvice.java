package com.ssn.spring.aop.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAdvice {

    @Pointcut("execution(* com.ssn.spring.aop.aspectj..foo*(int)) && args(intValue)")
    public void fooExecution(int intValue) {
    }

    @Pointcut("bean(myDependency*)")
    public void inMyDependency() {
    }

    @Before("fooExecution(intValue) && inMyDependency()")
    public void simpleBeforeAdvice(JoinPoint joinPoint, int intValue) {
        System.out.println("Executing: " +
                joinPoint.getSignature().getDeclaringTypeName()
                + joinPoint.getSignature().getName() + " argument: " + intValue);
    }
}
