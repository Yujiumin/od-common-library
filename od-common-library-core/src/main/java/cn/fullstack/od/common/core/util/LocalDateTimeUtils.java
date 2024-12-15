package cn.fullstack.od.common.core.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LocalDateTimeUtils {

    private static final String DEFAULT_DATETIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT_PATTERN);
    private static final ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();
    private static final ZoneOffset DEFAULT_ZONE_OFFSET = ZoneOffset.of("+8");


    public static String format(LocalDateTime localDateTime) {
        return format(localDateTime, DEFAULT_DATETIME_FORMATTER);
    }

    public static String format(LocalDateTime localDateTime, String pattern) {
        return format(localDateTime, DateTimeFormatter.ofPattern(pattern));
    }

    public static String format(LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter) {
        return localDateTime.format(dateTimeFormatter);
    }

    public static LocalDateTime parse(String formatVal) {
        return parse(formatVal, DEFAULT_DATETIME_FORMATTER);
    }

    public static LocalDateTime parse(String formatVal, String pattern) {
        return parse(formatVal, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime parse(String formatVal, DateTimeFormatter dateTimeFormatter) {
        return LocalDateTime.parse(formatVal, dateTimeFormatter);
    }

    public static Date toJavaDate(LocalDateTime localDateTime) {
        return toJavaDate(localDateTime,DEFAULT_ZONE_ID);
    }

    public static Date toJavaDate(LocalDateTime localDateTime, ZoneId zoneId) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        return toJavaDate(zonedDateTime);
    }

    public static Date toJavaDate(ZonedDateTime zonedDateTime) {
        Instant instant = zonedDateTime.toInstant();
        return toJavaDate(instant);
    }

    public static Date toJavaDate(Instant instant) {
        return Date.from(instant);
    }

    public static LocalDateTime parse(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return parse(instant);
    }

    public static LocalDateTime parse(Instant instant) {
        return parse(instant, DEFAULT_ZONE_ID);
    }

    public static LocalDateTime parse(Instant instant, ZoneId zoneId) {
        return LocalDateTime.ofInstant(instant, zoneId);
    }

    public static long toEpochMilli(LocalDateTime localDateTime) {
        return toEpochMilli(localDateTime, DEFAULT_ZONE_OFFSET);
    }

    public static long toEpochMilli(LocalDateTime localDateTime, ZoneOffset zoneOffset) {
        Instant instant = localDateTime.toInstant(zoneOffset);
        return instant.toEpochMilli();
    }

    public static long toEpochMilli(LocalDateTime localDateTime, ZoneId zoneId) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        Instant instant = zonedDateTime.toInstant();
        return instant.toEpochMilli();
    }
}
