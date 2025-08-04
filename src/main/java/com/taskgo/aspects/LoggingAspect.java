package com.taskgo.aspects;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Log4j2
public class LoggingAspect {

    @Pointcut("execution(* com.taskgo.controllers..*(..)) || execution(* com.taskgo.services..*(..))")
    public void applicationPackagePointcut() {}

    @Before("applicationPackagePointcut()")
    public void logMethodEntry(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        log.info("➡️ Entering: {} with args = {}", method, Arrays.toString(args));
    }

    @AfterReturning(pointcut = "applicationPackagePointcut()", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, Object result) {
        String method = joinPoint.getSignature().toShortString();
        log.info("✅ Exiting: {} with result = {}", method, result);
    }

    @AfterThrowing(pointcut = "applicationPackagePointcut()", throwing = "ex")
    public void logExceptions(JoinPoint joinPoint, Throwable ex) {
        String method = joinPoint.getSignature().toShortString();
        log.error("❌ Exception in {}: {}", method, ex.getMessage());
    }
}
