package com.nilo.wms.common.util;

import org.apache.commons.lang.RandomStringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by ronny on 2017/9/19.
 */
public class DateUtil {
    public static final String SHORT_FORMAT = "yyyyMMdd";
    public static final String LONG_FORMAT = "yyyyMMddHHmmss";
    public static final String WEB_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HHmmss";
    public static final String MONTH_FORMAT = "yyyyMM";
    public static final String CHINA_FORMAT = "yyyy年MM月dd日";
    public static final String LONG_WEB_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String LONG_WEB_FORMAT_NO_SEC = "yyyy-MM-dd HH:mm";

    public DateUtil() {
    }

    public static Long getSysTimeStamp() {
        return Long.valueOf(System.currentTimeMillis() / 1000L);
    }

    public static Long getTimeStamp(Date date) {
        return date == null ? null : Long.valueOf(date.getTime() / 1000L);
    }

    public static String format(Date date, String format) {
        return date != null && !StringUtil.isBlank(format) ? (new SimpleDateFormat(format)).format(date) : "";
    }


    public static String formatCurrent(String format) {
        return StringUtil.isBlank(format) ? "" : format(new Date(), format);
    }

    public static String format(Long time, String format) {
        if (time == null) return "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return StringUtil.isBlank(format) ? "" : sdf.format(new Date(time * 1000l));
    }


    public static String formatWeb(Date date) {
        return format(date, "yyyy-MM-dd");
    }


    public static String currentDateTime(String pattern, String timezoneId) {
        return format(System.currentTimeMillis(), pattern, timezoneId);
    }

    /**
     * 得到给定时间的给定天数后的日期
     * @return
     */
    public static Date getAppointDate(Date date, int day){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.getAppointDate(new Date(),30));
    }

    public static long[] getPeriodTime(char type, int delta, String timezoneId) {
        DateTimeZone timeZone = DateTimeZone.forID(timezoneId);
        DateTime p = (new DateTime()).withZone(timeZone);
        p = p.hourOfDay().setCopy(0).minuteOfHour().setCopy(0).secondOfMinute().setCopy(0);
        long begin;
        long end;
        switch (type) {
            case 'D':
                begin = p.plusDays(delta).getMillis() / 1000L;
                end = p.plusDays(delta + 1).getMillis() / 1000L - 1L;
                break;
            case 'M':
                p = p.dayOfMonth().setCopy(1);
            case 'm':
                begin = p.plusMonths(delta).getMillis() / 1000L;
                end = p.plusMonths(delta + 1).getMillis() / 1000L - 1L;
                break;
            case 'W':
                p = p.dayOfWeek().setCopy(1);
            case 'w':
                begin = p.plusWeeks(delta).getMillis() / 1000L;
                end = p.plusWeeks(delta + 1).getMillis() / 1000L - 1L;
                break;
            default:
                throw new IllegalArgumentException("unknown type " + type);
        }

        return new long[]{begin, end};
    }

    public static long parse(String value, String pattern) {
        return parse(value, pattern, "+00:00", Locale.ENGLISH);
    }

    public static long parse(String value, String pattern, String timezoneId) {
        return parse(value, pattern, timezoneId, Locale.ENGLISH);
    }

    public static long parse(String value, String pattern, String timezoneId, Locale locale) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
        DateTimeZone tz = DateTimeZone.forID(timezoneId);
        return formatter.withLocale(locale).withZone(tz).parseDateTime(value).getMillis() / 1000;
    }

    public static String format(long timestamp, String pattern, String timezoneId) {
        return format(timestamp, pattern, timezoneId, Locale.ENGLISH);
    }

    public static String format(long timestamp, String pattern, String timezoneId, Locale locale) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
        DateTimeZone tz = DateTimeZone.forID(timezoneId);
        return formatter.withLocale(locale).print(new DateTime(timestamp, tz));
    }
}
