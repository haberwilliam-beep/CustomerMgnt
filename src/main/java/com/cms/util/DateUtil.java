package com.cms.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateUtil {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DISPLAY_DATE_FORMAT = "dd/MM/yyyy";
    public static final String DISPLAY_DATETIME_FORMAT = "dd/MM/yyyy HH:mm";

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT);
    private static final DateTimeFormatter DISPLAY_DATE_FORMATTER = DateTimeFormatter.ofPattern(DISPLAY_DATE_FORMAT);
    private static final DateTimeFormatter DISPLAY_DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DISPLAY_DATETIME_FORMAT);

    private DateUtil() {}

    public static String format(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DATETIME_FORMATTER) : "";
    }

    public static String formatDisplay(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DISPLAY_DATETIME_FORMATTER) : "";
    }

    public static String format(LocalDate date) {
        return date != null ? date.format(DATE_FORMATTER) : "";
    }

    public static LocalDateTime parseDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, DATETIME_FORMATTER);
    }

    public static LocalDate parseDate(String dateStr) {
        return LocalDate.parse(dateStr, DATE_FORMATTER);
    }

    public static String now() {
        return LocalDateTime.now().format(DATETIME_FORMATTER);
    }
}
