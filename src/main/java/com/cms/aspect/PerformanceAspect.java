package com.cms.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class PerformanceAspect {

    private static final long SLOW_THRESHOLD_MS = 1000;

    @Pointcut("execution(* com.cms.service.*.*(..)) || execution(* com.cms.controller.*.*(..))")
    public void applicationLayer() {}

    @Around("applicationLayer()")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            long elapsedTime = System.currentTimeMillis() - startTime;
            String method = joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName();
            if (elapsedTime > SLOW_THRESHOLD_MS) {
                log.warn("SLOW EXECUTION: {} took {}ms (threshold: {}ms)", method, elapsedTime, SLOW_THRESHOLD_MS);
            } else {
                log.debug("PERFORMANCE: {} took {}ms", method, elapsedTime);
            }
        }
    }
}
