package com.cms.mapper;

import com.cms.model.AuditLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AuditLogMapper {
    AuditLog findById(@Param("id") Long id);
    List<AuditLog> findAll();
    List<AuditLog> findByFilters(Map<String, Object> filters);
    int countByFilters(Map<String, Object> filters);
    List<AuditLog> findByUserId(@Param("userId") Long userId);
    List<AuditLog> findByEntity(@Param("entity") String entity, @Param("entityId") String entityId);
    int insert(AuditLog auditLog);
    int deleteById(@Param("id") Long id);
}
