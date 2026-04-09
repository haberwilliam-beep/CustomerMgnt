package com.cms.controller;

import com.cms.model.Translation;
import com.cms.service.I18nService;
import com.cms.service.TranslationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/translation")
@RequiredArgsConstructor
public class TranslationController {

    private final TranslationService translationService;
    private final I18nService i18nService;

    @GetMapping("/manage")
    @PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
    public String manageTranslations(Model model) {
        model.addAttribute("translations", translationService.findAll());
        return "translation/manage";
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
    public String saveTranslation(@ModelAttribute Translation translation, RedirectAttributes redirectAttributes) {
        try {
            if (translation.getId() == null) {
                translationService.save(translation);
                redirectAttributes.addFlashAttribute("successMsg", "Translation saved successfully");
            } else {
                translationService.update(translation);
                redirectAttributes.addFlashAttribute("successMsg", "Translation updated successfully");
            }
            i18nService.evictCache(translation.getLang());
        } catch (Exception e) {
            log.error("Error saving translation", e);
            redirectAttributes.addFlashAttribute("errorMsg", "Error saving translation: " + e.getMessage());
        }
        return "redirect:/translation/manage";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteTranslation(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            Translation t = translationService.findById(id);
            if (t != null) {
                translationService.delete(id);
                i18nService.evictCache(t.getLang());
                redirectAttributes.addFlashAttribute("successMsg", "Translation deleted successfully");
            }
        } catch (Exception e) {
            log.error("Error deleting translation: {}", id, e);
            redirectAttributes.addFlashAttribute("errorMsg", "Error deleting translation");
        }
        return "redirect:/translation/manage";
    }
}
