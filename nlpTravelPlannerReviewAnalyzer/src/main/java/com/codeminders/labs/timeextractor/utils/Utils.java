package com.codeminders.labs.timeextractor.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.codeminders.labs.timeextractor.temporal.entities.Date;
import com.codeminders.labs.timeextractor.temporal.entities.Time;
import com.codeminders.labs.timeextractor.temporal.entities.TimeDate;

public class Utils {

    /* Method returns TimeDate from LocalDateTime */

    public static TimeDate getTimeDate(LocalDateTime localDate, int timezoneOffset) {
        TimeDate timeDate = new TimeDate();
        int day = localDate.getDayOfMonth();
        int month = localDate.getMonthOfYear();
        int year = localDate.getYear();
        timeDate.setRelative(true);
        Date date = new Date(year, month, day);
        int hours = localDate.getHourOfDay();
        int minutes = localDate.getMinuteOfHour();
        int seconds = localDate.getSecondOfMinute();
        Time time = new Time(hours, minutes, seconds, timezoneOffset);
        timeDate.setDate(date);
        timeDate.setTime(time);
        return timeDate;

    }

    public static TimeDate getTimeDate(LocalDateTime localDate) {
        return getTimeDate(localDate, -1000);
    }

    public static TimeDate getTimeDate(LocalDateTime localDate, int timezoneOffset, TimeDate timeDate) {
        TimeDate newTimeDate = getTimeDate(localDate, timezoneOffset);
        if (timeDate.getDate() != null) {
            newTimeDate.getDate().setDayOfWeek(timeDate.getDate().getDayOfWeek());
            newTimeDate.getDate().setWeekOfMonth(timeDate.getDate().getWeekOfMonth());
        }
        return newTimeDate;
    }

    /* Method returns LocalDateTime from TimeDate */

    public static LocalDateTime getTimeDate(TimeDate timeDate) {
        LocalDateTime localDateTime = new LocalDateTime();
        if (timeDate == null) {
            return localDateTime;
        }
        Date date = timeDate.getDate();
        Time time = timeDate.getTime();
        if (date != null) {
            if (date.getYear() != 0) {
                localDateTime = localDateTime.withYear(date.getYear());
            }
            if (date.getMonth() != 0) {
                localDateTime = localDateTime.withMonthOfYear(date.getMonth());
            }
            if (date.getDay() != 0) {
                localDateTime = localDateTime.withDayOfMonth(date.getDay());
            }
        }
        if (time != null) {
            localDateTime = localDateTime.withHourOfDay(time.getHours());
            localDateTime = localDateTime.withMinuteOfHour(time.getMinutes());
            localDateTime = localDateTime.withSecondOfMinute(time.getSeconds());
        }
        return localDateTime;

    }

    public static Matcher getMatch(String rule, String text) {
        Pattern p = Pattern.compile(rule, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(text);
        m.find();
        return m;
    }

    public static String currentLocalDate() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
        return localDate.toString(fmt);
    }

    public static int convertTime(int time, String aMPm) {
        if (aMPm.contains("p") || aMPm.contains("P")) {
            if (time >= 12 && time < 24) {
                return time;
            } else if (time == 24) {
                return 0;
            } else if (time >= 1 && time < 12) {
                return time + 12;
            } else if (time > 24) {
                return -1;
            }

        } else {
            if (time == 12 || time == 24) {
                return 0;
            } else if (time > 24) {
                return -1;
            } else {
                return time;
            }
        }
        return time;

    }

}
