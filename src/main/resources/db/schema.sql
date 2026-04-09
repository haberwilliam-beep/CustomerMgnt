-- Customer Management System - Database Schema
-- MySQL 8.x

CREATE DATABASE IF NOT EXISTS cms_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE cms_db;

-- Users table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL DEFAULT 'VIEWER',
    email VARCHAR(255),
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    created_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_users_username (username),
    INDEX idx_users_role (role)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Customers table
CREATE TABLE IF NOT EXISTS customers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name_en VARCHAR(255) NOT NULL,
    name_ar VARCHAR(255),
    phone VARCHAR(50),
    email VARCHAR(255),
    address TEXT,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    INDEX idx_customers_status (status),
    INDEX idx_customers_email (email),
    INDEX idx_customers_name_en (name_en)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Translations table
CREATE TABLE IF NOT EXISTS translations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    `key` VARCHAR(255) NOT NULL,
    lang VARCHAR(10) NOT NULL,
    value TEXT NOT NULL,
    created_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uk_translations_key_lang (`key`, lang),
    INDEX idx_translations_lang (lang)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Audit Logs table
CREATE TABLE IF NOT EXISTS audit_logs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    action VARCHAR(100) NOT NULL,
    entity VARCHAR(100),
    entity_id VARCHAR(100),
    timestamp DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    old_values LONGTEXT,
    new_values LONGTEXT,
    details TEXT,
    ip_address VARCHAR(50),
    correlation_id VARCHAR(100),
    INDEX idx_audit_logs_user_id (user_id),
    INDEX idx_audit_logs_entity (entity),
    INDEX idx_audit_logs_timestamp (timestamp),
    INDEX idx_audit_logs_action (action)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
