package com.cms.util;

import java.util.UUID;

public final class CorrelationIdUtil {

    private static final ThreadLocal<String> CORRELATION_ID = new ThreadLocal<>();
    public static final String HEADER_NAME = "X-Correlation-ID";

    private CorrelationIdUtil() {}

    public static String generate() {
        return UUID.randomUUID().toString();
    }

    public static void setCorrelationId(String correlationId) {
        CORRELATION_ID.set(correlationId);
    }

    public static String getCorrelationId() {
        String id = CORRELATION_ID.get();
        if (id == null) {
            id = generate();
            CORRELATION_ID.set(id);
        }
        return id;
    }

    public static void clear() {
        CORRELATION_ID.remove();
    }
}
