package com.cms.service;

import com.cms.mapper.AuditLogMapper;
import com.cms.model.AuditLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface AuditLogService {
    AuditLog findById(Long id);
    List<AuditLog> findAll();
    List<AuditLog> findByFilters(Map<String, Object> filters);
    int countByFilters(Map<String, Object> filters);
    List<AuditLog> findByUserId(Long userId);
    void logAction(Long userId, String action, String entity, String entityId,
                   String oldValues, String newValues, String details, String ipAddress, String correlationId);
}

@Slf4j
@Service
@RequiredArgsConstructor
class AuditLogServiceImpl implements AuditLogService {

    private static final org.apache.logging.log4j.Logger auditLogger =
            org.apache.logging.log4j.LogManager.getLogger("audit");

    private final AuditLogMapper auditLogMapper;

    @Override
    public AuditLog findById(Long id) {
        return auditLogMapper.findById(id);
    }

    @Override
    public List<AuditLog> findAll() {
        return auditLogMapper.findAll();
    }

    @Override
    public List<AuditLog> findByFilters(Map<String, Object> filters) {
        return auditLogMapper.findByFilters(filters);
    }

    @Override
    public int countByFilters(Map<String, Object> filters) {
        return auditLogMapper.countByFilters(filters);
    }

    @Override
    public List<AuditLog> findByUserId(Long userId) {
        return auditLogMapper.findByUserId(userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logAction(Long userId, String action, String entity, String entityId,
                          String oldValues, String newValues, String details,
                          String ipAddress, String correlationId) {
        AuditLog auditLog = AuditLog.builder()
                .userId(userId)
                .action(action)
                .entity(entity)
                .entityId(entityId)
                .timestamp(LocalDateTime.now())
                .oldValues(oldValues)
                .newValues(newValues)
                .details(details)
                .ipAddress(ipAddress)
                .correlationId(correlationId)
                .build();

        auditLogMapper.insert(auditLog);
        auditLogger.info("AUDIT | userId={} action={} entity={} entityId={} correlationId={}",
                userId, action, entity, entityId, correlationId);
    }
}
