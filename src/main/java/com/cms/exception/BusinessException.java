package com.cms.exception;

public class BusinessException extends CMSException {

    public BusinessException(String message) {
        super("BUSINESS_ERROR", message);
    }

    public BusinessException(String errorCode, String message) {
        super(errorCode, message);
    }

    public BusinessException(String message, Throwable cause) {
        super("BUSINESS_ERROR", message, cause);
    }
}
