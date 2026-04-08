package com.cms.aspect;

import com.cms.service.AuditLogService;
import com.cms.util.CorrelationIdUtil;
import com.cms.util.JsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {

    private final AuditLogService auditLogService;

    @Around("@annotation(com.cms.aspect.Auditable)")
    public Object auditMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Auditable auditable = method.getAnnotation(Auditable.class);

        String action = auditable.action().isEmpty() ? method.getName() : auditable.action();
        String entity = auditable.entity().isEmpty() ? joinPoint.getTarget().getClass().getSimpleName() : auditable.entity();

        Long userId = getCurrentUserId();
        String ipAddress = getClientIp();
        String correlationId = CorrelationIdUtil.getCorrelationId();
        String args = JsonUtil.toJson(joinPoint.getArgs());

        Object result = null;
        try {
            result = joinPoint.proceed();
            auditLogService.logAction(userId, action, entity, extractEntityId(joinPoint.getArgs()),
                    null, args, "Method: " + method.getName(), ipAddress, correlationId);
            return result;
        } catch (Throwable t) {
            log.error("Audited method threw exception: {}", t.getMessage());
            throw t;
        }
    }

    private Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && auth.getPrincipal() instanceof org.springframework.security.core.userdetails.UserDetails ud) {
            return 0L; // Resolve actual user ID from UserDetailsService if needed
        }
        return null;
    }

    private String getClientIp() {
        try {
            ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attrs != null) {
                HttpServletRequest request = attrs.getRequest();
                String ip = request.getHeader("X-Forwarded-For");
                return (ip != null && !ip.isEmpty()) ? ip.split(",")[0].trim() : request.getRemoteAddr();
            }
        } catch (Exception e) {
            log.debug("Could not get client IP", e);
        }
        return "unknown";
    }

    private String extractEntityId(Object[] args) {
        if (args != null && args.length > 0 && args[0] != null) {
            return args[0].toString();
        }
        return "N/A";
    }
}
