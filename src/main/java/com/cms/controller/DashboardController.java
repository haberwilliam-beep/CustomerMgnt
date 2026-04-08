package com.cms.controller;

import com.cms.service.CustomerService;
import com.cms.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final CustomerService customerService;
    private final UserService userService;

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model, Authentication authentication) {
        log.debug("Dashboard accessed by: {}", authentication.getName());
        model.addAttribute("username", authentication.getName());
        model.addAttribute("totalCustomers", customerService.findAll().size());
        model.addAttribute("totalUsers", userService.findAll().size());
        return "dashboard";
    }
}
