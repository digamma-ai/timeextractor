package ai.digamma.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ai.digamma.exceptions.ExceptionMessages;
import ai.digamma.temporal.entities.Date;
import ai.digamma.temporal.entities.Time;
import ai.digamma.temporal.entities.TimeDate;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * <h1>Utils Class</h1> provides different static help methods, such as timedate
 * convertors, json object convertors, etc.
 *
 * @author Anastasiia Mishchuk
 * @version 1.0
 * @since 2014-10-28
 */
public class Utils {

    private Utils() {
        // This class is not meant to be instantiated
    }


    public static String dateInUTC(java.util.Date currentTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy hh:mm:ss a z");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(currentTime);
    }

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

    public static Set<UUID> getSetofUUIDsFromString(String arrayOfUUIDs) throws Exception {
        Set<UUID> setOfUUIDs = new HashSet<UUID>();
        String[] UUIDs = arrayOfUUIDs.split(",");
        for (String UUId : UUIDs) {
            try {
                UUId = UUId.replace("\"", "").replace("[", "").replace("]", "").trim();
                UUID uuid = UUID.fromString(UUId);
                setOfUUIDs.add(uuid);
            } catch (Exception ex) {
                throw new Exception(ExceptionMessages.FIELD_RULES + ": " + UUId);
            }
        }
        return setOfUUIDs;

    }

    public static TimeDate getTimeDate(LocalDateTime localDate) {
        return getTimeDate(localDate, -1000);
    }

    public static TimeDate getTimeDateUTC(LocalDateTime localDate) {
        return getTimeDate(localDate, 0);
    }

    public static TimeDate getTimeDate(LocalDateTime localDate, int timezoneOffset, TimeDate timeDate) {
        TimeDate newTimeDate = getTimeDate(localDate, timezoneOffset);
        if (timeDate.getDate() != null) {
            newTimeDate.getDate().setDayOfWeek(timeDate.getDate().getDayOfWeek());
            newTimeDate.getDate().setWeekOfMonth(timeDate.getDate().getWeekOfMonth());
        }
        if (timeDate.getTime() != null) {
            String tz = timeDate.getTime().getTimezoneName();
            newTimeDate.getTime().setTimezoneName(tz);
        }
        return newTimeDate;
    }

    /* Method returns LocalDateTime from TimeDate */

    public static LocalDateTime getTimeDate(TimeDate timeDate, LocalDateTime localDateTime) {
        if (timeDate == null) {
            return localDateTime;
        }
        if (localDateTime == null) {
            localDateTime = new LocalDateTime();
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

    public static java.util.Date offsetTimeZone(java.util.Date date, String timezoneName) {

        TimeZone fromTimeZone = TimeZone.getTimeZone(timezoneName);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(fromTimeZone);
        calendar.setTime(date);
        System.out.println(fromTimeZone.getDSTSavings());
        if (fromTimeZone.inDaylightTime(calendar.getTime())) {
            calendar.add(Calendar.MILLISECOND, calendar.getTimeZone().getDSTSavings() * -1);
        }
        return calendar.getTime();

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
