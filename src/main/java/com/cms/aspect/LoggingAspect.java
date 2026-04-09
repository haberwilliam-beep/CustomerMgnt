package com.cms.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.cms.service.*.*(..))")
    public void serviceLayer() {}

    @Pointcut("execution(* com.cms.controller.*.*(..))")
    public void controllerLayer() {}

    @Before("serviceLayer() || controllerLayer()")
    public void logBefore(JoinPoint joinPoint) {
        log.debug("Entering: {}.{}()", joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "serviceLayer()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.debug("Returned from: {}.{}()", joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "serviceLayer() || controllerLayer()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        log.error("Exception in: {}.{}() - {}", joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName(), ex.getMessage());
    }
}
