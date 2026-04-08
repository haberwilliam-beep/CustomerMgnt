package com.cms.controller;

import com.cms.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/audit")
@RequiredArgsConstructor
public class AuditLogController {

    private final AuditLogService auditLogService;

    @GetMapping("/logs")
    @PreAuthorize("hasRole('ADMIN')")
    public String viewLogs(
            @RequestParam(value = "entity", required = false) String entity,
            @RequestParam(value = "action", required = false) String action,
            Model model) {

        Map<String, Object> filters = new HashMap<>();
        if (entity != null && !entity.isEmpty()) filters.put("entity", entity);
        if (action != null && !action.isEmpty()) filters.put("action", action);

        model.addAttribute("auditLogs", auditLogService.findByFilters(filters));
        model.addAttribute("entity", entity);
        model.addAttribute("action", action);
        return "audit/logs";
    }
}
