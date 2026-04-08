package com.cms.controller;

import com.cms.model.Customer;
import com.cms.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model model) {
        log.debug("Listing customers");
        model.addAttribute("customers", customerService.findAll());
        return "customer/list";
    }

    @GetMapping("/edit")
    @PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
    public String editCustomers(Model model) {
        log.debug("Edit customers page");
        return "customer/edit";
    }

    @GetMapping("/form")
    @PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
    public String newCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/form";
    }

    @GetMapping("/form/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
    public String editCustomerForm(@PathVariable Long id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customer/form";
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
    public String saveCustomer(@ModelAttribute Customer customer,
                               Authentication authentication,
                               RedirectAttributes redirectAttributes) {
        try {
            if (customer.getId() == null) {
                customerService.createCustomer(customer, authentication.getName());
                redirectAttributes.addFlashAttribute("successMsg", "Customer created successfully");
            } else {
                customerService.updateCustomer(customer, authentication.getName());
                redirectAttributes.addFlashAttribute("successMsg", "Customer updated successfully");
            }
        } catch (Exception e) {
            log.error("Error saving customer", e);
            redirectAttributes.addFlashAttribute("errorMsg", "Error saving customer: " + e.getMessage());
        }
        return "redirect:/customer/list";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
    public String deleteCustomer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            customerService.deleteCustomer(id);
            redirectAttributes.addFlashAttribute("successMsg", "Customer deleted successfully");
        } catch (Exception e) {
            log.error("Error deleting customer: {}", id, e);
            redirectAttributes.addFlashAttribute("errorMsg", "Error deleting customer");
        }
        return "redirect:/customer/list";
    }
}
