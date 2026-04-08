package com.cms.dao;

import com.cms.model.AuditLog;

import java.util.List;
import java.util.Map;

public interface AuditLogDAO {
    AuditLog findById(Long id);
    List<AuditLog> findAll();
    List<AuditLog> findByFilters(Map<String, Object> filters);
    int countByFilters(Map<String, Object> filters);
    List<AuditLog> findByUserId(Long userId);
    int save(AuditLog auditLog);
}
