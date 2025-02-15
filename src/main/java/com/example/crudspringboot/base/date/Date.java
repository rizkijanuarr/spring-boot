package com.example.crudspringboot.base.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Date {

    // ISO 8601 (Default JSON API)
    private static final DateTimeFormatter ISO_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    // Format "10/09/2024"
    private static final DateTimeFormatter SLASH_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Format "10-09-2024"
    private static final DateTimeFormatter DASH_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Format "10 Sept 2024"
    private static final DateTimeFormatter SHORT_MONTH_FORMATTER =
            DateTimeFormatter.ofPattern("dd MMM yyyy", new Locale("id", "ID"));

    // Format "Selasa, 10 September 2024"
    private static final DateTimeFormatter FULL_DAY_FORMATTER =
            DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", new Locale("id", "ID"));

    // Format "14:30:45"
    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    // Format "10 Sept 2024 14:30"
    private static final DateTimeFormatter SHORT_DATETIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm", new Locale("id", "ID"));

    // =================== METODE UTILITAS ===================

    public static String formatIso(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(ISO_FORMATTER) : null;
    }

    public static String formatSlash(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(SLASH_FORMATTER) : null;
    }

    public static String formatDash(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DASH_FORMATTER) : null;
    }

    public static String formatShortMonth(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(SHORT_MONTH_FORMATTER) : null;
    }

    public static String formatFullDay(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(FULL_DAY_FORMATTER) : null;
    }

    public static String formatTime(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(TIME_FORMATTER) : null;
    }

    public static String formatShortDateTime(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(SHORT_DATETIME_FORMATTER) : null;
    }
}

