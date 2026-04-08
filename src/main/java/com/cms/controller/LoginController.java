package com.cms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class LoginController {

    private static final org.apache.logging.log4j.Logger securityLogger =
            org.apache.logging.log4j.LogManager.getLogger("security");

    @GetMapping("/login")
    public String loginPage(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {

        if (error != null) {
            model.addAttribute("errorMsg", "Invalid username or password.");
            securityLogger.warn("Failed login attempt");
        }
        if (logout != null) {
            model.addAttribute("logoutMsg", "You have been logged out successfully.");
            securityLogger.info("User logged out");
        }
        return "login";
    }

    @GetMapping("/error/403")
    public String accessDenied() {
        return "error/403";
    }
}
