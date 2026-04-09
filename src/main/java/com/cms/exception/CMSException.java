package com.cms.exception;

public class CMSException extends RuntimeException {

    private final String errorCode;

    public CMSException(String message) {
        super(message);
        this.errorCode = "CMS_ERROR";
    }

    public CMSException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public CMSException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "CMS_ERROR";
    }

    public CMSException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
