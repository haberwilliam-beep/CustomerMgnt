package com.cms.controller;

import com.cms.model.ApiResponse;
import com.cms.model.Customer;
import com.cms.model.Translation;
import com.cms.service.AuditLogService;
import com.cms.service.CustomerService;
import com.cms.service.TranslationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final CustomerService customerService;
    private final TranslationService translationService;
    private final AuditLogService auditLogService;

    @GetMapping("/customers")
    public ResponseEntity<Map<String, Object>> getCustomers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int rows,
            @RequestParam(required = false) String sidx,
            @RequestParam(required = false) String sord,
            @RequestParam(required = false) String searchField,
            @RequestParam(required = false) String searchString) {

        Map<String, Object> filters = new HashMap<>();
        if (searchField != null && searchString != null && !searchString.isEmpty()) {
            filters.put(searchField, searchString);
        }
        filters.put("offset", (page - 1) * rows);
        filters.put("limit", rows);
        if (sidx != null) filters.put("orderBy", sidx + " " + (sord != null ? sord : "asc"));

        List<Customer> customers = customerService.findByFilters(filters);
        int total = customerService.countByFilters(filters);
        int totalPages = (int) Math.ceil((double) total / rows);

        Map<String, Object> response = new HashMap<>();
        response.put("page", page);
        response.put("total", totalPages);
        response.put("records", total);
        response.put("rows", customers);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<ApiResponse<Customer>> getCustomer(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ApiResponse.ok(customer));
    }

    @PostMapping("/customers")
    public ResponseEntity<ApiResponse<Customer>> createCustomer(
            @RequestBody Customer customer, Authentication authentication) {
        Customer created = customerService.createCustomer(customer, authentication.getName());
        return ResponseEntity.ok(ApiResponse.ok(created, "Customer created successfully"));
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<ApiResponse<Customer>> updateCustomer(
            @PathVariable Long id, @RequestBody Customer customer, Authentication authentication) {
        customer.setId(id);
        Customer updated = customerService.updateCustomer(customer, authentication.getName());
        return ResponseEntity.ok(ApiResponse.ok(updated, "Customer updated successfully"));
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCustomer(@PathVariable Long id) {
        boolean deleted = customerService.deleteCustomer(id);
        if (deleted) {
            return ResponseEntity.ok(ApiResponse.ok(null, "Customer deleted successfully"));
        }
        return ResponseEntity.badRequest().body(ApiResponse.error("Customer not found"));
    }

    @GetMapping("/translations/{lang}")
    public ResponseEntity<ApiResponse<List<Translation>>> getTranslations(@PathVariable String lang) {
        List<Translation> translations = translationService.findByLang(lang);
        return ResponseEntity.ok(ApiResponse.ok(translations));
    }

    @GetMapping("/audit")
    public ResponseEntity<Map<String, Object>> getAuditLogs(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int rows) {

        Map<String, Object> filters = new HashMap<>();
        filters.put("offset", (page - 1) * rows);
        filters.put("limit", rows);

        var logs = auditLogService.findByFilters(filters);
        int total = auditLogService.countByFilters(filters);
        int totalPages = (int) Math.ceil((double) total / rows);

        Map<String, Object> response = new HashMap<>();
        response.put("page", page);
        response.put("total", totalPages);
        response.put("records", total);
        response.put("rows", logs);
        return ResponseEntity.ok(response);
    }
}
