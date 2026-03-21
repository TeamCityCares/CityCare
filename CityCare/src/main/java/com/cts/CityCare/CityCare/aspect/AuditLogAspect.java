package com.cts.CityCare.CityCare.aspect;

import com.cts.CityCare.CityCare.entity.AuditLog;
import com.cts.CityCare.CityCare.entity.User;
import com.cts.CityCare.CityCare.repository.AuditLogRepository;
import com.cts.CityCare.CityCare.repository.UserRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class AuditLogAspect {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    private UserRepository userRepository;

    // Intercept any save() method in any repository
    @Pointcut("execution(* com.cts.CityCare.CityCare.repository.*.save*(..))")
    public void repositorySaveMethods() {}

    // Intercept any delete() method in any repository
    @Pointcut("execution(* com.cts.CityCare.CityCare.repository.*.delete*(..))")
    public void repositoryDeleteMethods() {}

    @AfterReturning(pointcut = "repositorySaveMethods()", returning = "result")
    public void logSaveActivity(JoinPoint joinPoint, Object result) {
        if (result == null || result instanceof AuditLog) {
            return; // Prevent infinite loops! Don't audit the audit logs.
        }
        
        String resourceName = result.getClass().getSimpleName();
        saveAuditLog("SAVED", resourceName);
    }

    @AfterReturning(pointcut = "repositoryDeleteMethods()")
    public void logDeleteActivity(JoinPoint joinPoint) {
        // For delete, we might not get the object returned, so we use the argument type
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0 && args[0] != null) {
            Object entity = args[0];
            if (entity instanceof AuditLog) return;
            
            String resourceName = entity.getClass().getSimpleName();
            saveAuditLog("DELETED", resourceName);
        } else {
            saveAuditLog("DELETED", "Unknown Resource");
        }
    }

    private void saveAuditLog(String action, String resource) {
        User currentUser = getCurrentUser();

        AuditLog log = AuditLog.builder()
                .action(action)
                .resource(resource)
                .timestamp(LocalDateTime.now())
                .user(currentUser)
                .build();

        auditLogRepository.save(log);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            return null; // No logged-in user (e.g., during registration)
        }

        String email = authentication.getName(); // Usually the email or username
        return userRepository.findByEmail(email).orElse(null);
    }
}
