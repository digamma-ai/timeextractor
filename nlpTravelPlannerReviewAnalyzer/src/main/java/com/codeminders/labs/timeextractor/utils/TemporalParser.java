package com.codeminders.labs.timeextractor.utils;

import java.util.ArrayList;
import java.util.Calendar;

import org.joda.time.LocalDate;

import com.codeminders.labs.timeextractor.temporal.entities.Date;
import com.codeminders.labs.timeextractor.temporal.entities.DayOfWeek;
import com.codeminders.labs.timeextractor.temporal.entities.Duration;
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

        if (holidayName.equalsIgnoreCase(Holidays.NEW_YEAR)) {
            Date startDate = new Date(0, 1, 1);
            Date endDate = new Date(0, 1, 1);
            start.setDate(startDate);
            end.setDate(endDate);
            temporal = new Temporal(start, end);

        }
        if (holidayName.equalsIgnoreCase(Holidays.HALLOWEEN)) {
            Date startDate = new Date(0, 10, 31);
            Date endDate = new Date(0, 10, 31);
            start.setDate(startDate);
            end.setDate(endDate);
            temporal = new Temporal(start, end);
        }

        if (holidayName.contains("Valentine")) {
            Date startDate = new Date(0, 2, 14);
            Date endDate = new Date(0, 2, 14);
            start.setDate(startDate);
            end.setDate(endDate);
            temporal = new Temporal(start, end);
        }

        if (holidayName.equalsIgnoreCase(Holidays.CHRISTMAS)) {
            Date startDate = new Date(0, 12, 25);
            Date endDate = new Date(0, 12, 25);
            start.setDate(startDate);
            end.setDate(endDate);
            temporal = new Temporal(start, end);
        }

        if (holidayName.equalsIgnoreCase(Holidays.THANKSGIVING)) {
            Date startDate = new Date(0, 11, 0);
            startDate.setWeekOfMonth(WeekOfMonth.FOURTH);
            Date endDate = new Date(0, 11, 0);
            start.setDate(startDate);
            end.setDate(endDate);
            endDate.setWeekOfMonth(WeekOfMonth.FOURTH);
            temporal = new Temporal(start, end);
        }

        if (holidayName.equalsIgnoreCase(Holidays.INDEPENDENCE_DAY)) {
            Date startDate = new Date(0, 7, 4);
            Date endDate = new Date(0, 7, 4);
            start.setDate(startDate);
            end.setDate(endDate);
            temporal = new Temporal(start, end);

        }
        if (temporal != null) {
            temporal.setType(Type.DATE);
        }
        return temporal;
    }

    public Temporal getRelativeTemporalObjectByProperty(String property, LocalDate localDate) {

        if (property.equalsIgnoreCase("today")) {
            TimeDate timeDate = getTimeDate(localDate);
            return TemporalObjectGenerator.generateTemporalTime(Type.DATE, timeDate);

        }
        if (property.equalsIgnoreCase("tomorrow")) {
            localDate = localDate.plusDays(1);
            TimeDate timeDate = getTimeDate(localDate);
            return TemporalObjectGenerator.generateTemporalTime(Type.DATE, timeDate);
        }

        if (property.equalsIgnoreCase("yesterday")) {
            localDate = localDate.minusDays(1);
            TimeDate timeDate = getTimeDate(localDate);
            return TemporalObjectGenerator.generateTemporalTime(Type.DATE, timeDate);

        }

        if (property.equalsIgnoreCase("the day before yesterday")) {
            localDate = localDate.minusDays(2);
            TimeDate timeDate = getTimeDate(localDate);
            return TemporalObjectGenerator.generateTemporalTime(Type.DATE, timeDate);
        }
        return null;
    }

    // Method returns TimeDate from localDate

    private TimeDate getTimeDate(LocalDate localDate) {
        TimeDate timeDate = new TimeDate();
        int day = localDate.getDayOfMonth();
        int month = localDate.getMonthOfYear();
        int year = localDate.getYear();
        timeDate.setRelative(true);
        Date date = new Date(year, month, day);
        timeDate.setDate(date);
        return timeDate;

    }

    public Temporal getDuration(String durationType, int durationLength) {
        Duration duration = null;
        if (durationType == null) {
            return null;
        }
        if (durationType.equalsIgnoreCase("minutes") || durationType.equalsIgnoreCase("minute") || durationType.equalsIgnoreCase("mins") || durationType.equalsIgnoreCase("min")
                || durationType.equalsIgnoreCase("mns") || durationType.equalsIgnoreCase("mn")) {
            duration = new Duration();
            duration.setMinutes(durationLength);
        }

        if (durationType.equalsIgnoreCase("hours") || durationType.equalsIgnoreCase("hour") || durationType.equalsIgnoreCase("hrs")) {
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

        if (durationType.equalsIgnoreCase("days") || durationType.equalsIgnoreCase("day")) {
            duration = new Duration();
            duration.setDays(durationLength);
        }

        if (durationType.equalsIgnoreCase("seconds") || durationType.equalsIgnoreCase("secs") || durationType.equalsIgnoreCase("sec")) {
            duration = new Duration();
            duration.setSeconds(durationLength);
        }

        return TemporalObjectGenerator.generateTemporalDuration(Type.DURATION, duration);

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
        if (timeOfDay.equalsIgnoreCase("noon") || timeOfDay.equalsIgnoreCase("noons")) {
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
