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

    /**
     * @deprecated
     */
    @Deprecated
    public static Long getTimeStamp(Date date) {
        return date == null ? null : Long.valueOf(date.getTime() / 1000L);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String getDateByTimeStamp(Long timeStamp, String format, Locale locale) {
        if (timeStamp != null && !StringUtil.isBlank(format) && locale != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
            return sdf.format(new Date(timeStamp.longValue() * 1000L));
        } else {
            return "";
        }
    }


    public static String format(Date date, String format) {
        return date != null && !StringUtil.isBlank(format) ? (new SimpleDateFormat(format)).format(date) : "";
    }


    public static String formatCurrent(String format) {
        return StringUtil.isBlank(format) ? "" : format(new Date(), format);
    }

    public static String format(Long time, String format) {
        if(time ==null) return "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return StringUtil.isBlank(format) ? "" : sdf.format(new Date(time * 1000l));
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String formatShort(Date date) {
        return format(date, "yyyyMMdd");
    }

    public static String formatWeb(Date date) {
        return format(date, "yyyy-MM-dd");
    }


    /**
     * @deprecated
     */
    @Deprecated
    public static String formatMonth(Date date) {
        return format(date, "yyyyMM");
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String formatTime(Date date) {
        return format(date, "HHmmss");
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String getTimestamp(int n) {
        return formatCurrent("yyyyMMddHHmmss") + RandomStringUtils.randomNumeric(n);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String getYesterdayDate(String format) {
        return getDateCompareToday(format, -1, 0);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String getDateCompareToday(String format, int daysAfter, int monthAfter) {
        Calendar today = Calendar.getInstance();
        if (daysAfter != 0) {
            today.add(5, daysAfter);
        }

        if (monthAfter != 0) {
            today.add(2, monthAfter);
        }

        return format(today.getTime(), format);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String getLastMonth(String format) {
        Calendar today = Calendar.getInstance();
        today.add(2, -1);
        return format(today.getTime(), format);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String getDateStrByTimeStamp(Long timeStamp, String format, String localeCode) {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone(localeCode));
        c.setTimeInMillis(timeStamp.longValue() * 1000L);
        String year = String.valueOf(c.get(1));
        int month = c.get(2) + 1;
        String monthStr = String.valueOf(month);
        if (month < 10) {
            monthStr = "0" + monthStr;
        }

        String day = c.get(5) < 10 ? "0" + String.valueOf(c.get(5)) : String.valueOf(c.get(5));
        String hour = c.get(11) < 10 ? "0" + String.valueOf(c.get(11)) : String.valueOf(c.get(11));
        String minute = c.get(12) < 10 ? "0" + String.valueOf(c.get(12)) : String.valueOf(c.get(12));
        String second = c.get(13) < 10 ? "0" + String.valueOf(c.get(13)) : String.valueOf(c.get(13));
        String time = year + monthStr + day + hour + minute + second;
        return format(parseDateLongFormat(time), format);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static Date parseDateLongFormat(String sDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date d = null;
        if (sDate != null && sDate.length() == "yyyyMMddHHmmss".length()) {
            try {
                d = dateFormat.parse(sDate);
            } catch (ParseException var4) {
                return null;
            }
        }

        return d;
    }

    public static String currentDateTime(String pattern, String timezoneId) {
        return format(System.currentTimeMillis(), pattern, timezoneId);
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
