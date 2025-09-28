package com.example.springtp.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class authorizationAspect {

    // works exactly like Node.js authorization middleware functions

    // Pointcut : all the StoreService functions
    @Around("execution(* com.example.springtp.service.PlayersService.*(..))")
    public Object checkSecurity(ProceedingJoinPoint pjp) throws Throwable {

        // @Around → the execution will be within the real function, and we can decide if we run it or not
        // ProceedingJoinPoint → just like à JoinPoint, but gives the ability to continue or block the function execution via proceed().

        // Ici, on fait une vérification simple des droits
        System.out.println("SECURITY -> Vérification des droits pour: " + pjp.getSignature().getName());

        boolean authorized = true;

        if (!authorized) {
            throw new RuntimeException("Access denied!");
        }

        // if authorized, proceed and execute the function
        return pjp.proceed();
    }

}
