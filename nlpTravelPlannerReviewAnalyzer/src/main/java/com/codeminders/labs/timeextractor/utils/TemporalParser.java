package com.codeminders.labs.timeextractor.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import com.codeminders.labs.timeextractor.rules.date.relative.NTimeAgoRule;
import com.codeminders.labs.timeextractor.rules.date.relative.NTimeAgoRule2;
import com.codeminders.labs.timeextractor.temporal.entities.Date;
import com.codeminders.labs.timeextractor.temporal.entities.DayOfWeek;
import com.codeminders.labs.timeextractor.temporal.entities.Duration;
import com.codeminders.labs.timeextractor.temporal.entities.DurationInterval;
import com.codeminders.labs.timeextractor.temporal.entities.Frequency;
import com.codeminders.labs.timeextractor.temporal.entities.Holidays;
import com.codeminders.labs.timeextractor.temporal.entities.Set;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Time;
import com.codeminders.labs.timeextractor.temporal.entities.TimeDate;
import com.codeminders.labs.timeextractor.temporal.entities.Type;
import com.codeminders.labs.timeextractor.temporal.entities.WeekOfMonth;

public class TemporalParser {

    public Temporal getSeason(String season, int year) {
        if (year == 0) {
            year = new LocalDate().getYear();
        }
        if (season == null || season.isEmpty()) {
            return null;
        }

        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        if (season.equalsIgnoreCase("Summer")) {
            Date startDate = new Date(year, 6, 1);
            Date endDate = new Date(year, 8, 31);
            start.setDate(startDate);
            end.setDate(endDate);
            return new Temporal(start, end);

        }
        if (season.equalsIgnoreCase("Winter")) {
            Date startDate = new Date(year, 12, 1);
            // as end date will be in new year
            year = year + 1;
            boolean leap = isLeapYear(year);
            Date endDate = new Date(year, 2, 28);
            if (leap) {
                endDate = new Date(2, 29);
            }
            start.setDate(startDate);
            end.setDate(endDate);
            return new Temporal(start, end);
        }
        if (season.equalsIgnoreCase("Autumn") || season.equalsIgnoreCase("Fall")) {
            Date startDate = new Date(year, 9, 1);
            Date endDate = new Date(year, 11, 30);
            start.setDate(startDate);
            end.setDate(endDate);
            return new Temporal(start, end);
        }
        if (season.equalsIgnoreCase("Spring")) {
            Date startDate = new Date(year, 3, 1);
            Date endDate = new Date(year, 5, 31);
            start.setDate(startDate);
            end.setDate(endDate);
            return new Temporal(start, end);
        }
        return null;
    }

    public boolean isLeapYear(int year) {
        if (year == 0) {
            return false;
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
    }

    public Temporal getHoliday(String holidayName) {

        if (holidayName == null || holidayName.isEmpty()) {
            return null;
        }
        Temporal temporal = null;
        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        if (holidayName.equalsIgnoreCase(Holidays.NEW_YEAR) || holidayName.equalsIgnoreCase(Holidays.NEW_YEAR2)
                || holidayName.equalsIgnoreCase(Holidays.NEW_YEAR3) || holidayName.equalsIgnoreCase(Holidays.NEW_YEAR4)
                || holidayName.equalsIgnoreCase(Holidays.NEW_YEAR5)) {
            Date startDate = new Date(0, 1, 1);
            Date endDate = new Date(0, 1, 1);
            start.setDate(startDate);
            end.setDate(endDate);
            temporal = new Temporal(start, end);

        } else if (holidayName.equalsIgnoreCase(Holidays.HALLOWEEN)) {
            Date startDate = new Date(0, 10, 31);
            Date endDate = new Date(0, 10, 31);
            start.setDate(startDate);
            end.setDate(endDate);
            temporal = new Temporal(start, end);
        }

        else if (holidayName.contains("Valentine")) {
            Date startDate = new Date(0, 2, 14);
            Date endDate = new Date(0, 2, 14);
            start.setDate(startDate);
            end.setDate(endDate);
            temporal = new Temporal(start, end);
        }

        else if (holidayName.equalsIgnoreCase(Holidays.CHRISTMAS) || holidayName.equalsIgnoreCase(Holidays.CHRISTMAS2)
                || holidayName.equalsIgnoreCase(Holidays.CHRISTMAS3)) {
            Date startDate = new Date(0, 12, 25);
            Date endDate = new Date(0, 12, 25);
            start.setDate(startDate);
            end.setDate(endDate);
            temporal = new Temporal(start, end);
        }

        else if (holidayName.equalsIgnoreCase(Holidays.THANKSGIVING) || holidayName.equalsIgnoreCase(Holidays.THANKSGIVING2)) {
            Date startDate = new Date(0, 11, 0);
            startDate.setWeekOfMonth(WeekOfMonth.FOURTH);
            Date endDate = new Date(0, 11, 0);
            start.setDate(startDate);
            end.setDate(endDate);
            endDate.setWeekOfMonth(WeekOfMonth.FOURTH);
            temporal = new Temporal(start, end);
        }

        else if (holidayName.equalsIgnoreCase(Holidays.INDEPENDENCE_DAY)) {
            Date startDate = new Date(0, 7, 4);
            Date endDate = new Date(0, 7, 4);
            start.setDate(startDate);
            end.setDate(endDate);
            temporal = new Temporal(start, end);

        }

        else if (holidayName.equalsIgnoreCase(Holidays.INAUGURATION_DAY)) {
            Date startDate = new Date(0, 1, 20);
            Date endDate = new Date(0, 1, 20);
            start.setDate(startDate);
            end.setDate(endDate);
            temporal = new Temporal(start, end);

        }

        else if (holidayName.equalsIgnoreCase(Holidays.MLK_DAY1) || holidayName.equalsIgnoreCase(Holidays.MLK_DAY2)) {
            Date startDate = new Date(0, 1, 0);
            startDate.setWeekOfMonth(WeekOfMonth.THIRD);
            startDate.setDayOfWeek(DayOfWeek.MO);
            Date endDate = new Date(0, 1, 0);
            endDate.setWeekOfMonth(WeekOfMonth.THIRD);
            endDate.setDayOfWeek(DayOfWeek.MO);
            start.setDate(startDate);
            end.setDate(endDate);
            temporal = new Temporal(start, end);

        }

        else if (holidayName.equalsIgnoreCase(Holidays.MLK_DAY1) || holidayName.equalsIgnoreCase(Holidays.MLK_DAY2)) {
            Date startDate = new Date(0, 1, 0);
            startDate.setWeekOfMonth(WeekOfMonth.THIRD);
            startDate.setDayOfWeek(DayOfWeek.MO);
            Date endDate = new Date(0, 1, 0);
            endDate.setWeekOfMonth(WeekOfMonth.THIRD);
            endDate.setDayOfWeek(DayOfWeek.MO);
            start.setDate(startDate);
            end.setDate(endDate);
            temporal = new Temporal(start, end);

        }

        else if (holidayName.equalsIgnoreCase(Holidays.WASHINGTON_DAY)) {
            Date startDate = new Date(0, 2, 0);
            startDate.setWeekOfMonth(WeekOfMonth.THIRD);
            startDate.setDayOfWeek(DayOfWeek.MO);
            Date endDate = new Date(0, 2, 0);
            endDate.setWeekOfMonth(WeekOfMonth.THIRD);
            endDate.setDayOfWeek(DayOfWeek.MO);
            start.setDate(startDate);
            end.setDate(endDate);
            temporal = new Temporal(start, end);

        }

        else if (holidayName.equalsIgnoreCase(Holidays.MEMORIAL)) {
            Date startDate = new Date(0, 5, 0);
            startDate.setWeekOfMonth(WeekOfMonth.LAST);
            startDate.setDayOfWeek(DayOfWeek.MO);
            Date endDate = new Date(0, 5, 0);
            endDate.setWeekOfMonth(WeekOfMonth.LAST);
            endDate.setDayOfWeek(DayOfWeek.MO);
            start.setDate(startDate);
            end.setDate(endDate);
            temporal = new Temporal(start, end);

        }

        else if (holidayName.equalsIgnoreCase(Holidays.LABOR)) {
            Date startDate = new Date(0, 9, 0);
            startDate.setWeekOfMonth(WeekOfMonth.FIRST);
            startDate.setDayOfWeek(DayOfWeek.MO);
            Date endDate = new Date(0, 9, 0);
            endDate.setWeekOfMonth(WeekOfMonth.FIRST);
            endDate.setDayOfWeek(DayOfWeek.MO);
            start.setDate(startDate);
            end.setDate(endDate);
            temporal = new Temporal(start, end);

        }

        else if (holidayName.equalsIgnoreCase(Holidays.COLUMBUS_DAY)) {
            Date startDate = new Date(0, 10, 0);
            startDate.setWeekOfMonth(WeekOfMonth.SECOND);
            startDate.setDayOfWeek(DayOfWeek.MO);
            Date endDate = new Date(0, 10, 0);
            endDate.setWeekOfMonth(WeekOfMonth.SECOND);
            endDate.setDayOfWeek(DayOfWeek.MO);
            start.setDate(startDate);
            end.setDate(endDate);
            temporal = new Temporal(start, end);

        }

        else if (holidayName.equalsIgnoreCase(Holidays.VETERANS_DAY)) {
            Date startDate = new Date(0, 11, 1);
            Date endDate = new Date(0, 11, 11);
            start.setDate(startDate);
            end.setDate(endDate);
            temporal = new Temporal(start, end);

        }

        if (temporal != null) {
            temporal.setType(Type.DATE);
        }
        return temporal;
    }

    public Temporal getRelativeTemporalObjectByProperty(String property, LocalDateTime localDate) {
        if (localDate == null) {
            localDate = new LocalDateTime();
        }
        if (property.equalsIgnoreCase("today")) {
            TimeDate timeDate = Utils.getTimeDateUTC(localDate);
            Time time = new Time(localDate.getHourOfDay(), localDate.getMinuteOfHour(), 0);
            time.setTimezone(0);
            timeDate.setTime(time);
            return TemporalObjectGenerator.generateTemporalTime(Type.DATE, timeDate);

        }
        if (property.equalsIgnoreCase("tomorrow")) {
            localDate = localDate.plusDays(1);
            Time time = new Time(localDate.getHourOfDay(), localDate.getMinuteOfHour(), 0);
            time.setTimezone(0);
            TimeDate timeDate = Utils.getTimeDateUTC(localDate);
            timeDate.setTime(time);

            return TemporalObjectGenerator.generateTemporalTime(Type.DATE, timeDate);
        }

        if (property.equalsIgnoreCase("tonight")) {
            localDate = localDate.plusDays(1);
            TimeDate timeDate = Utils.getTimeDateUTC(localDate);
            Time time = new Time(19, 0, 0);
            time.setTimezone(0);

            timeDate.setTime(time);

            return TemporalObjectGenerator.generateTemporalTime(Type.DATE, timeDate);
        }

        if (property.equalsIgnoreCase("yesterday")) {
            localDate = localDate.minusDays(1);
            TimeDate timeDate = Utils.getTimeDateUTC(localDate);
            Time time = new Time(localDate.getHourOfDay(), localDate.getMinuteOfHour(), 0);
            time.setTimezone(0);
            return TemporalObjectGenerator.generateTemporalTime(Type.DATE, timeDate);

        }

        if (property.equalsIgnoreCase("the day before yesterday")) {
            localDate = localDate.minusDays(2);
            TimeDate timeDate = Utils.getTimeDateUTC(localDate);
            Time time = new Time(localDate.getHourOfDay(), localDate.getMinuteOfHour(), 0);
            time.setTimezone(0);
            return TemporalObjectGenerator.generateTemporalTime(Type.DATE, timeDate);
        }
        return null;
    }

    /* get by dayOfWeek */

    public Temporal getRelativeTemporalObjectByDayOfWeek(DayOfWeek dayOfWeek, LocalDateTime localDate) {
        int currentDayOfWeek = localDate.getDayOfWeek();
        int parsedDayOfWeek = dayOfWeek.getValue();
        if (parsedDayOfWeek >= currentDayOfWeek) {
            localDate = localDate.plusDays(parsedDayOfWeek - currentDayOfWeek);
        } else if (currentDayOfWeek > parsedDayOfWeek) {
            localDate = localDate.plusDays(7 + parsedDayOfWeek - currentDayOfWeek);
        }
        Time time = new Time(localDate.getHourOfDay(), localDate.getMinuteOfHour(), 0);
        TimeDate timeDate = Utils.getTimeDate(localDate);
        time.setTimezone(0);
        timeDate.setTime(time);
        Temporal temporal = TemporalObjectGenerator.generateTemporalTime(Type.DATE, timeDate);
        temporal.getStartDate().getDate().setDayOfWeek(dayOfWeek);
        temporal.getEndDate().getDate().setDayOfWeek(dayOfWeek);
        return temporal;
    }

    /* get by dayOfWeek and weekOfMonth */

    public Temporal getRelativeTemporalObjectByWeekOfMonth(DayOfWeek dayOfWeek, WeekOfMonth weekOfMonth, LocalDateTime localDate) {
        int currentDayOfWeek = localDate.getDayOfWeek();
        int currentWeek = currentWeekOfMonth(localDate);

        int parsedDayOfWeek = dayOfWeek.getValue();
        int parsedWeekOfMonth = weekOfMonth.getValue();

        if (weekOfMonth == WeekOfMonth.LAST) {
            int day = getLastWeekdayOfMonth(parsedDayOfWeek, localDate.getMonthOfYear(), localDate.getYear());
            if (day > localDate.getDayOfMonth()) {
                localDate = localDate.withDayOfMonth(day);
            } else {
                day = getLastWeekdayOfMonth(parsedDayOfWeek, localDate.plusMonths(1).getMonthOfYear(), localDate.getYear());
                localDate = localDate.withDayOfMonth(day);
            }
        } else if (weekOfMonth == WeekOfMonth.FIRST) {
            LocalDateTime newLocalDate = nthWeekdayOfMonth(parsedDayOfWeek, localDate.getMonthOfYear(), localDate.getYear(), 1);
            if (newLocalDate.getDayOfMonth() < localDate.getDayOfMonth()) {
                localDate = nthWeekdayOfMonth(parsedDayOfWeek, localDate.getMonthOfYear() + 1, localDate.getYear(), 1);
            } else {
                localDate = newLocalDate;
            }

        } else if (currentWeek > parsedWeekOfMonth) {
            localDate = nthWeekdayOfMonth(dayOfWeek.getValue(), localDate.getMonthOfYear() + 1, localDate.getYear(), weekOfMonth.getValue());
        } else if (currentWeek < parsedWeekOfMonth) {
            localDate = nthWeekdayOfMonth(dayOfWeek.getValue(), localDate.getMonthOfYear(), localDate.getYear(), weekOfMonth.getValue());
        } else if (currentWeek == parsedWeekOfMonth) {
            if (parsedDayOfWeek < currentDayOfWeek) {
                localDate = nthWeekdayOfMonth(dayOfWeek.getValue(), localDate.getMonthOfYear() + 1, localDate.getYear(), weekOfMonth.getValue());
            } else {
                localDate = nthWeekdayOfMonth(dayOfWeek.getValue(), localDate.getMonthOfYear(), localDate.getYear(), weekOfMonth.getValue());
            }
        } else if (currentWeek == weekOfMonth.getValue() && currentDayOfWeek < parsedDayOfWeek) {
            localDate = nthWeekdayOfMonth(dayOfWeek.getValue(), localDate.getMonthOfYear(), localDate.getYear(), weekOfMonth.getValue());
        }

        TimeDate timeDate = Utils.getTimeDate(localDate);

        Temporal temporal = TemporalObjectGenerator.generateTemporalTime(Type.DATE, timeDate);
        temporal.getStartDate().getDate().setDayOfWeek(dayOfWeek);
        temporal.getStartDate().getDate().setWeekOfMonth(weekOfMonth);
        temporal.getEndDate().getDate().setDayOfWeek(dayOfWeek);
        temporal.getEndDate().getDate().setWeekOfMonth(weekOfMonth);
        return temporal;

    }

    private LocalDateTime nthWeekdayOfMonth(int dayOfWeek, int month, int year, int n) {
        LocalDateTime start = new LocalDateTime(year, month, 1, 0, 0);
        LocalDateTime date = start.withDayOfWeek(dayOfWeek);
        return (date.isBefore(start)) ? date.plusWeeks(n) : date.plusWeeks(n - 1);
    }

    private int currentWeekOfMonth(LocalDateTime localDate) {
        int date = localDate.getDayOfMonth();
        int weekOfMonth = date % 7;

        return weekOfMonth;
    }

    private int getLastWeekdayOfMonth(int dayweek, int month, int year) {
        LocalDateTime d = new LocalDateTime(year, month, 1, 0, 0).plusMonths(1).withDayOfWeek(dayweek);
        if (d.getMonthOfYear() != month)
            d = d.minusWeeks(1);
        return d.getDayOfMonth();
    }

    public Temporal getDurationInterval(Temporal duration1, Temporal duration2) {
        Temporal temporal = new Temporal();
        temporal.setType(Type.DURATION_INTERVAL);
        DurationInterval interval = new DurationInterval(duration1.getDuration(), duration2.getDuration());
        temporal.setDurationInterval(interval);
        return temporal;
    }

    public Temporal getDuration(String durationType, int durationLength) {
        Duration duration = null;
        if (durationType == null) {
            return null;
        }
        if (durationType.equalsIgnoreCase("minutes") || durationType.equalsIgnoreCase("minute") || durationType.equalsIgnoreCase("mins")
                || durationType.equalsIgnoreCase("min") || durationType.equalsIgnoreCase("mns") || durationType.equalsIgnoreCase("mn")) {
            duration = new Duration();
            duration.setMinutes(durationLength);
        }

        if (durationType.equalsIgnoreCase("hours") || durationType.equalsIgnoreCase("hour") || durationType.equalsIgnoreCase("hrs")
                || durationType.equalsIgnoreCase("hr")) {
            duration = new Duration();
            duration.setHours(durationLength);
        }

        if (durationType.equalsIgnoreCase("weeks") || durationType.equalsIgnoreCase("week")) {
            duration = new Duration();
            duration.setWeeks(durationLength);
        }

        if (durationType.equalsIgnoreCase("months") || durationType.equalsIgnoreCase("month")) {
            duration = new Duration();
            duration.setMonths(durationLength);
        }

        if (durationType.equalsIgnoreCase("years") || durationType.equalsIgnoreCase("year")) {
            duration = new Duration();
            duration.setYears(durationLength);
        }

        if (durationType.equalsIgnoreCase("days") || durationType.equalsIgnoreCase("day") || durationType.equalsIgnoreCase("nights")
                || durationType.equalsIgnoreCase("night")) {
            duration = new Duration();
            duration.setDays(durationLength);
        }

        if (durationType.equalsIgnoreCase("seconds") || durationType.equalsIgnoreCase("secs") || durationType.equalsIgnoreCase("sec")) {
            duration = new Duration();
            duration.setSeconds(durationLength);
        }

        return TemporalObjectGenerator.generateTemporalDuration(Type.DURATION, duration);

    }

    public Temporal getRelativeDurationDate(String text, LocalDateTime dateTime) {
        Matcher m = Utils.getMatch(new NTimeAgoRule().getRule(), text);
        int durationLength = Integer.parseInt(m.group(1));
        String durationType = m.group(3);
        if (dateTime == null) {
            dateTime = LocalDateTime.now();
        }
        if (durationType == null) {
            return null;
        }
        if (durationType.equalsIgnoreCase("minutes") || durationType.equalsIgnoreCase("minute") || durationType.equalsIgnoreCase("mins")
                || durationType.equalsIgnoreCase("min") || durationType.equalsIgnoreCase("mns") || durationType.equalsIgnoreCase("mn")) {
            dateTime = dateTime.minusMinutes(durationLength);

        }

        if (durationType.equalsIgnoreCase("hours") || durationType.equalsIgnoreCase("hour") || durationType.equalsIgnoreCase("hrs")
                || durationType.equalsIgnoreCase("hr")) {
            dateTime = dateTime.minusHours(durationLength);

        }

        if (durationType.equalsIgnoreCase("weeks") || durationType.equalsIgnoreCase("week")) {
            dateTime = dateTime.minusWeeks(durationLength);

        }

        if (durationType.equalsIgnoreCase("months") || durationType.equalsIgnoreCase("month")) {
            dateTime = dateTime.minusMonths(durationLength);

        }

        if (durationType.equalsIgnoreCase("years") || durationType.equalsIgnoreCase("year")) {
            dateTime = dateTime.minusYears(durationLength);

        }

        if (durationType.equalsIgnoreCase("days") || durationType.equalsIgnoreCase("day")) {
            dateTime = dateTime.minusDays(durationLength);

        }

        if (durationType.equalsIgnoreCase("seconds") || durationType.equalsIgnoreCase("secs") || durationType.equalsIgnoreCase("sec")) {
            dateTime = dateTime.minusSeconds(durationLength);

        }
        TimeDate timeDate = Utils.getTimeDate(dateTime);
        timeDate.getTime().setTimezone(0);
        Temporal temporal = TemporalObjectGenerator.generateTemporalTime(Type.DATE, timeDate);
        return temporal;

    }

    public Temporal getRelativeDurationDate2(String text, LocalDateTime dateTime) {
        Matcher m = Utils.getMatch(new NTimeAgoRule2().getRule(), text);
        int durationLength = TemporalBasicCaseParser.getIntFromBasicTerm(m.group(2));
        String durationType = m.group(8);
        if (dateTime == null) {
            dateTime = LocalDateTime.now();
        }
        if (durationType == null) {
            return null;
        }
        if (durationType.equalsIgnoreCase("minutes") || durationType.equalsIgnoreCase("minute") || durationType.equalsIgnoreCase("mins")
                || durationType.equalsIgnoreCase("min") || durationType.equalsIgnoreCase("mns") || durationType.equalsIgnoreCase("mn")) {
            dateTime = dateTime.minusMinutes(durationLength);

        }

        if (durationType.equalsIgnoreCase("hours") || durationType.equalsIgnoreCase("hour") || durationType.equalsIgnoreCase("hrs")
                || durationType.equalsIgnoreCase("hr")) {
            dateTime = dateTime.minusHours(durationLength);

        }

        if (durationType.equalsIgnoreCase("weeks") || durationType.equalsIgnoreCase("week")) {
            dateTime = dateTime.minusWeeks(durationLength);

        }

        if (durationType.equalsIgnoreCase("months") || durationType.equalsIgnoreCase("month")) {
            dateTime = dateTime.minusMonths(durationLength);

        }

        if (durationType.equalsIgnoreCase("years") || durationType.equalsIgnoreCase("year")) {
            dateTime = dateTime.minusYears(durationLength);

        }

        if (durationType.equalsIgnoreCase("days") || durationType.equalsIgnoreCase("day")) {
            dateTime = dateTime.minusDays(durationLength);

        }

        if (durationType.equalsIgnoreCase("seconds") || durationType.equalsIgnoreCase("secs") || durationType.equalsIgnoreCase("sec")) {
            dateTime = dateTime.minusSeconds(durationLength);

        }
        TimeDate timeDate = Utils.getTimeDate(dateTime);
        timeDate.getTime().setTimezone(0);
        Temporal temporal = TemporalObjectGenerator.generateTemporalTime(Type.DATE, timeDate);
        return temporal;

    }

    public Temporal getTimeOfDay(String timeOfDay) {
        Temporal temporal = null;
        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();
        Type type = Type.TIME_INTERVAL;

        if (timeOfDay.equalsIgnoreCase("morning") || timeOfDay.equalsIgnoreCase("mornings")) {
            Time startTime = new Time(5, 0, 0);
            Time endTime = new Time(12, 0, 0);
            start.setTime(startTime);
            end.setTime(endTime);
            temporal = new Temporal(start, end, type);

        }
        if (timeOfDay.equalsIgnoreCase("afternoon") || timeOfDay.equalsIgnoreCase("afternoons")) {
            Time startTime = new Time(12, 0, 0);
            Time endTime = new Time(18, 0, 0);
            start.setTime(startTime);
            end.setTime(endTime);
            temporal = new Temporal(start, end, type);
        }
        if (timeOfDay.equalsIgnoreCase("evening") || timeOfDay.equalsIgnoreCase("evenings")) {
            Time startTime = new Time(18, 0, 0);
            Time endTime = new Time(22, 0, 0);
            start.setTime(startTime);
            end.setTime(endTime);
            temporal = new Temporal(start, end);
        }
        if (timeOfDay.equalsIgnoreCase("night") || timeOfDay.equalsIgnoreCase("nights")) {
            Time startTime = new Time(22, 0, 0);
            Time endTime = new Time(23, 59, 59);
            start.setTime(startTime);
            end.setTime(endTime);
            temporal = new Temporal(start, end, type);

        }
        if (timeOfDay.equalsIgnoreCase("midnight") || timeOfDay.equalsIgnoreCase("midnights")) {
            Time startTime = new Time(0, 0, 0);
            Time endTime = new Time(0, 0, 0);
            start.setTime(startTime);
            end.setTime(endTime);
            temporal = new Temporal(start, end, type);

        }
        if (timeOfDay.equalsIgnoreCase("noon") || timeOfDay.equalsIgnoreCase("noons") || timeOfDay.equalsIgnoreCase("lunch time")) {
            Time startTime = new Time(12, 0, 0);
            Time endTime = new Time(12, 0, 0);
            start.setTime(startTime);
            end.setTime(endTime);
            temporal = new Temporal(start, end, type);

        }
        if (timeOfDay.equalsIgnoreCase("midday") || timeOfDay.equalsIgnoreCase("middays")) {
            Time startTime = new Time(12, 0, 0);
            Time endTime = new Time(12, 0, 0);
            start.setTime(startTime);
            end.setTime(endTime);
            temporal = new Temporal(start, end, type);
        }
        return temporal;

    }

    public Temporal getTemporalForEveryPeriod(String period) {
        Temporal temporal = new Temporal();
        Set set = new Set();
        temporal.setType(Type.SET);

        if (period.equalsIgnoreCase("day") || period.equalsIgnoreCase("daily")) {
            set.setFrequency(Frequency.DAILY);
            temporal.setSet(set);
            return temporal;

        } else if (period.equalsIgnoreCase("week") || period.equalsIgnoreCase("weekly")) {
            set.setFrequency(Frequency.WEEKLY);
            temporal.setSet(set);
            return temporal;

        } else if (period.equalsIgnoreCase("month") || period.equalsIgnoreCase("monthly")) {
            set.setFrequency(Frequency.MONTHLY);
            temporal.setSet(set);
            return temporal;

        } else if (period.equalsIgnoreCase("year") || period.equalsIgnoreCase("yearly") || period.equalsIgnoreCase("annual")) {
            set.setFrequency(Frequency.YEARLY);
            temporal.setSet(set);
            return temporal;
        }

        else if (period.equalsIgnoreCase("weekend")) {
            set.setFrequency(Frequency.WEEKLY);
            ArrayList<DayOfWeek> daysOfWeek = new ArrayList<DayOfWeek>();
            daysOfWeek.add(DayOfWeek.SA);
            daysOfWeek.add(DayOfWeek.SU);
            set.setByDay(daysOfWeek);
            temporal.setSet(set);
            return temporal;
        }

        else if (period.equalsIgnoreCase("hourly")) {
            set.setFrequency(Frequency.HOURLY);
            temporal.setSet(set);
            return temporal;

        }

        else if (period.equalsIgnoreCase("weekday")) {
            set.setFrequency(Frequency.WEEKLY);
            ArrayList<DayOfWeek> daysOfWeek = new ArrayList<DayOfWeek>();
            daysOfWeek.add(DayOfWeek.MO);
            daysOfWeek.add(DayOfWeek.TU);
            daysOfWeek.add(DayOfWeek.WE);
            daysOfWeek.add(DayOfWeek.TH);
            daysOfWeek.add(DayOfWeek.FR);
            set.setByDay(daysOfWeek);
            temporal.setSet(set);
            return temporal;
        } else {
            return null;
        }

    }

}
