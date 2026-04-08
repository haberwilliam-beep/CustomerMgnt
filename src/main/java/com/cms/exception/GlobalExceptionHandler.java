package com.cms.exception;

import com.cms.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Object handleBusinessException(BusinessException ex, HttpServletRequest request) {
        log.error("BusinessException: {} - {}", ex.getErrorCode(), ex.getMessage());
        if (isApiRequest(request)) {
            return ResponseEntity.badRequest().body(ApiResponse.error(ex.getMessage()));
        }
        ModelAndView mav = new ModelAndView("error/500");
        mav.addObject("errorMessage", ex.getMessage());
        mav.addObject("errorCode", ex.getErrorCode());
        return mav;
    }

    @ExceptionHandler(CMSException.class)
    public Object handleCMSException(CMSException ex, HttpServletRequest request) {
        log.error("CMSException: {} - {}", ex.getErrorCode(), ex.getMessage());
        if (isApiRequest(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error(ex.getMessage()));
        }
        ModelAndView mav = new ModelAndView("error/500");
        mav.addObject("errorMessage", ex.getMessage());
        return mav;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Object handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        log.warn("Access denied: {}", ex.getMessage());
        if (isApiRequest(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiResponse.error("Access denied"));
        }
        return new ModelAndView("error/403");
    }

    @ExceptionHandler(Exception.class)
    public Object handleGenericException(Exception ex, HttpServletRequest request) {
        log.error("Unhandled exception: {}", ex.getMessage(), ex);
        if (isApiRequest(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error("Internal server error"));
        }
        ModelAndView mav = new ModelAndView("error/500");
        mav.addObject("errorMessage", "An unexpected error occurred");
        return mav;
    }

    private boolean isApiRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("X-Requested-With");
        String accept = request.getHeader("Accept");
        String uri = request.getRequestURI();
        return "XMLHttpRequest".equals(requestedWith)
                || (accept != null && accept.contains("application/json"))
                || uri.startsWith("/api/");
    }
}
