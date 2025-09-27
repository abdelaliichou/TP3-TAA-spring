package com.example.springtp.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class traceAspect {

    // Pointcut : all the public methods in the service classes
    @Before("execution(public * com.example.springtp.service..*(..))")
    public void logBefore(JoinPoint joinPoint) {

        // JoinPoint → contains infos about the called function (class, function, arguments).
        // @Before → this function will be executed before the real function.

        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println("TRACE -> Classe: " + className + ", Méthode: " + methodName);
    }

}
