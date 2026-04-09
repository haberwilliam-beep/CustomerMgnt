-- Customer Management System - Sample Data
USE cms_db;

-- Insert users with BCrypt hashed passwords
-- admin/admin123
-- operator/operator123
-- viewer/viewer123
INSERT INTO users (username, password, role, email, enabled) VALUES
('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6NHmy', 'ADMIN', 'admin@cms.com', TRUE),
('operator', '$2a$10$rPOT/bTOFNqKQpunYk2IHO.vcFbGnXJ0C6EJvFSdBqRnH3CZUL8.S', 'OPERATOR', 'operator@cms.com', TRUE),
('viewer', '$2a$10$K2KevRwC9Cj9.VPnqVMr.OJsmvZbDKOKwLKJfZ.cQ5MfR1rK4Kdci', 'VIEWER', 'viewer@cms.com', TRUE)
ON DUPLICATE KEY UPDATE updated_date = CURRENT_TIMESTAMP;

-- Insert sample customers
INSERT INTO customers (name_en, name_ar, phone, email, address, status, created_by, updated_by) VALUES
('John Smith', 'جون سميث', '+1-555-0101', 'john.smith@example.com', '123 Main St, New York, NY 10001', 'ACTIVE', 'admin', 'admin'),
('Jane Doe', 'جين دو', '+1-555-0102', 'jane.doe@example.com', '456 Oak Ave, Los Angeles, CA 90001', 'ACTIVE', 'admin', 'admin'),
('Mohammed Al-Ahmad', 'محمد الأحمد', '+966-50-1234567', 'mohammed@example.com', 'Al-Olaya District, Riyadh, Saudi Arabia', 'ACTIVE', 'operator', 'operator'),
('Sarah Johnson', 'سارة جونسون', '+44-20-7946-0958', 'sarah.j@example.com', '10 Downing Street, London, UK', 'INACTIVE', 'admin', 'admin'),
('Ahmed Hassan', 'أحمد حسن', '+20-100-1234567', 'ahmed.hassan@example.com', 'Tahrir Square, Cairo, Egypt', 'ACTIVE', 'operator', 'admin')
ON DUPLICATE KEY UPDATE updated_date = CURRENT_TIMESTAMP;

-- Insert translations (English)
INSERT INTO translations (`key`, lang, value) VALUES
('app.title', 'en', 'Customer Management System'),
('nav.dashboard', 'en', 'Dashboard'),
('nav.customers', 'en', 'Customers'),
('nav.translations', 'en', 'Translations'),
('nav.audit', 'en', 'Audit Logs'),
('nav.logout', 'en', 'Logout'),
('customer.list', 'en', 'Customer List'),
('action.save', 'en', 'Save'),
('action.edit', 'en', 'Edit'),
('action.delete', 'en', 'Delete'),
('action.cancel', 'en', 'Cancel'),
('action.add', 'en', 'Add New'),
('status.active', 'en', 'Active'),
('status.inactive', 'en', 'Inactive'),
('msg.success', 'en', 'Operation completed successfully'),
('msg.error', 'en', 'An error occurred')
ON DUPLICATE KEY UPDATE value = VALUES(value), updated_date = CURRENT_TIMESTAMP;

-- Insert translations (Arabic)
INSERT INTO translations (`key`, lang, value) VALUES
('app.title', 'ar', 'نظام إدارة العملاء'),
('nav.dashboard', 'ar', 'لوحة التحكم'),
('nav.customers', 'ar', 'العملاء'),
('nav.translations', 'ar', 'الترجمات'),
('nav.audit', 'ar', 'سجلات التدقيق'),
('nav.logout', 'ar', 'تسجيل الخروج'),
('customer.list', 'ar', 'قائمة العملاء'),
('action.save', 'ar', 'حفظ'),
('action.edit', 'ar', 'تعديل'),
('action.delete', 'ar', 'حذف'),
('action.cancel', 'ar', 'إلغاء'),
('action.add', 'ar', 'إضافة جديد'),
('status.active', 'ar', 'نشط'),
('status.inactive', 'ar', 'غير نشط'),
('msg.success', 'ar', 'تمت العملية بنجاح'),
('msg.error', 'ar', 'حدث خطأ')
ON DUPLICATE KEY UPDATE value = VALUES(value), updated_date = CURRENT_TIMESTAMP;
