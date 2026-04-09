package com.cms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {
    private Long id;
    private Long userId;
    private String action;
    private String entity;
    private String entityId;
    private LocalDateTime timestamp;
    private String oldValues;
    private String newValues;
    private String details;
    private String ipAddress;
    private String correlationId;
}
