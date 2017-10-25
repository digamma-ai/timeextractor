package ai.digamma.service.process;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.TreeSet;

import ai.digamma.entities.Settings;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.temporal.entities.TimeDate;
import ai.digamma.temporal.entities.Type;
import ai.digamma.temporal.entities.WeekOfMonth;
import ai.digamma.utils.TemporalParser;
import org.joda.time.LocalDateTime;

import ai.digamma.entities.TemporalExtraction;
import ai.digamma.temporal.entities.DayOfWeek;
import ai.digamma.temporal.entities.Time;
import ai.digamma.utils.Utils;

/**
 * <h1>Process Rules Service Class</h1> is used for processing extracted time
 * expressions: setting specified timezone, finding closes relative date in case
 * of day of week, etc.
 *
 * @author Anastasiia Mishchuk
 * @version 1.0
 * @since 2014-10-28
 */

public class ProcessRulesService {

    private TemporalParser parser;

    public ProcessRulesService() {
        parser = new TemporalParser();
    }

    /**
     * Method is used to find closest date according to current date for
     * relative date (today, yesterday, etc.)
     */
    public TreeSet<TemporalExtraction> processRelativeDate(TreeSet<TemporalExtraction> receivedTemporals, Settings settings) {
        List<TemporalExtraction> list = new ArrayList<TemporalExtraction>(receivedTemporals);
        receivedTemporals = relativeDate(list, settings.getDate());
        return receivedTemporals;
    }

    /**
     * Method is used to find closest day of week according to current date for
     * relative date (Sunday, Monday, Second Tuesdday of the month)
     */
    public TreeSet<TemporalExtraction> processRelativeDayOfWeek(TreeSet<TemporalExtraction> receivedTemporals, Settings settings) {
        List<TemporalExtraction> list = new ArrayList<TemporalExtraction>(receivedTemporals);
        receivedTemporals = daysOfWeekRelative(list, settings.getDate());
        return receivedTemporals;
    }

    /**
     * Method changes found time expression temporal objects according to
     * specified date and timezone
     * 
     * @param receivedTemporals
     * @param settings
     * @return
     */
    public TreeSet<TemporalExtraction> changeExpressionsAccordingToUserTimeZoneAndCurrentDate(TreeSet<TemporalExtraction> receivedTemporals, Settings settings) {
        Iterator<TemporalExtraction> itr = receivedTemporals.iterator();
        int offsetTimeZone = settings.getTimezoneOffset();
        while (itr.hasNext()) {
            TemporalExtraction te = itr.next();
            List<Temporal> temporals = te.getTemporal();
            for (int i = 0; i < temporals.size(); i++) {
                Temporal temporal = temporals.get(i);
                if (temporal != null) {
                    TimeDate startTimeDate = temporal.getStartDate();
                    if (startTimeDate != null) {
                        startTimeDate = summerTime(startTimeDate, settings.getDate());
                        startTimeDate = convertDateAndOffset(startTimeDate, offsetTimeZone, settings.getDate());
                        temporal.setStartDate(startTimeDate);
                    }
                    TimeDate endTimeDate = temporal.getEndDate();
                    if (endTimeDate != null) {
                        endTimeDate = summerTime(endTimeDate, settings.getDate());
                        endTimeDate = convertDateAndOffset(endTimeDate, offsetTimeZone, settings.getDate());
                        temporal.setEndDate(endTimeDate);
                    }
                }
            }
        }

        return receivedTemporals;
    }

    /**
     * 
     * Method is used to convert date in case summer time is observed
     *
     */
    private TimeDate summerTime(TimeDate timeDate, LocalDateTime localDateTime) {
        if (timeDate == null) {
            return null;
        }
        if (summerTimeIsObserved(timeDate)) {
            LocalDateTime localDateTimeStart = Utils.getTimeDate(timeDate, localDateTime);
            localDateTimeStart = localDateTimeStart.minusMinutes(60);
            timeDate = Utils.getTimeDate(localDateTimeStart, timeDate.getTime().getTimezoneOffset(), timeDate);
        }
        return timeDate;
    }

    /**
     * Method is used to convert date with user specified timezone offset
     *
     */
    private TimeDate convertDateAndOffset(TimeDate timeDate, int offsetTimeZone, LocalDateTime localDateTime) {
        if (timeDate == null) {
            return null;
        }
        Time time = timeDate.getTime();
        if (timeDate != null && time != null && time.getTimezoneOffset() == -1000) {
            LocalDateTime localDateTimeStart = Utils.getTimeDate(timeDate, localDateTime);
            localDateTimeStart = localDateTimeStart.plusMinutes(offsetTimeZone);
            timeDate = Utils.getTimeDate(localDateTimeStart, 0, timeDate);
        } else if (timeDate != null && time != null) {
            LocalDateTime localDateTimeStart = Utils.getTimeDate(timeDate, localDateTime);
            localDateTimeStart = localDateTimeStart.plusMinutes(timeDate.getTime().getTimezoneOffset());
            timeDate = Utils.getTimeDate(localDateTimeStart, timeDate.getTime().getTimezoneOffset(), timeDate);
        } else {
            LocalDateTime localDateTimeStart = Utils.getTimeDate(timeDate, localDateTime);
            timeDate = Utils.getTimeDate(localDateTimeStart, offsetTimeZone, timeDate);
        }

        return timeDate;

    }

    /**
     * Method is used to check whether found timeDate observes summer time
     *
     */
    private boolean summerTimeIsObserved(TimeDate timeDate) {
        if (timeDate == null || timeDate.getTime() == null || timeDate.getTime().getTimezoneName() == null) {
            return false;
        }
        if (timeDate.getTime().getTimezoneName() == null) {
            return false;
        }
        String timezone = timeDate.getTime().getTimezoneName();
        TimeZone tz = TimeZone.getTimeZone(timezone);
        Calendar c = Calendar.getInstance(tz);
        Date date = new Date();
        c.setTime(date);
        int offset = c.get(Calendar.DST_OFFSET);
        if (offset > 0) {
            return true;
        }
        return false;

    }

    private TreeSet<TemporalExtraction> daysOfWeekRelative(List<TemporalExtraction> list, LocalDateTime dateTime) {
        if (dateTime == null) {
            dateTime = new LocalDateTime();
        }

        for (int i = 0; i < list.size(); i++) {
            TemporalExtraction extraction = list.get(i);
            Temporal temporal = extraction.getTemporal().get(0);
            if (temporal.getType() == Type.DAY_OF_WEEK_WEEK_OF_MONTH || temporal.getType() == Type.DAY_OF_WEEK_WEEK_OF_MONTH_TIME_INTERVAL_INDIRECT
                    || temporal.getType() == Type.DAY_OF_WEEK_WEEK_OF_MONTH_SET || temporal.getType() == Type.DAY_OF_WEEK_WEEK_OF_MONTH_TIME_INTERVAL_INDIRECT
                    || temporal.getType() == Type.DAY_OF_WEEK_WEEK_OF_MONTH_TIME_INTERVAL_SET || temporal.getType() == Type.DAY_OF_WEEK_WEEK_OF_MONTH_TIME_INTERVAL_INDIRECT_SET) {
                Temporal newTemporal = relativedayOfWeekWeekOfMonth(temporal, dateTime);
                list.get(i).getTemporal().set(0, newTemporal);
            }

            else if (temporal.getType() == Type.DAY_OF_WEEK_INTERVAL || temporal.getType() == Type.DAY_OF_WEEK_INTERVAL_TIME) {
                Temporal newTemporal = relativeDayOfWeekInterval(temporal, dateTime);
                list.get(i).getTemporal().set(0, newTemporal);
            }

            else if (temporal.getType() == Type.DAY_OF_WEEK || temporal.getType() == Type.DAY_OF_WEEK_AND_TIME || temporal.getType() == Type.DAY_OF_WEEK_TIME_INTERVAL_INDIRECT
                    || temporal.getType() == Type.DAY_OF_WEEK_SET) {
                Temporal newTemporal = relativeDayOfWeek(temporal, dateTime);
                list.get(i).getTemporal().set(0, newTemporal);
            }
        }
        return new TreeSet<TemporalExtraction>(list);
    }

    private Temporal relativedayOfWeekWeekOfMonth(Temporal temporal, LocalDateTime dateTime) {
        if (temporal.getStartDate() == null) {
            temporal.setStartDate(temporal.getEndDate());
        }
        DayOfWeek dayOfWeek = temporal.getStartDate().getDate().getDayOfWeek();
        WeekOfMonth weekOfMonth = temporal.getStartDate().getDate().getWeekOfMonth();
        if (temporal.getStartDate().getDate().getMonth() != 0) {
            dateTime = dateTime.withMonthOfYear(temporal.getStartDate().getDate().getMonth());
            dateTime = dateTime.withDayOfMonth(1);

        }
        Temporal newTemporal = parser.getRelativeTemporalObjectByWeekOfMonth(dayOfWeek, weekOfMonth, dateTime);
        newTemporal.setRule(temporal.getRule());
        newTemporal.setGroup(temporal.getGroup());
        if (temporal.getStartDate() != null && temporal.getStartDate().getTime() != null) {
            Time time = temporal.getStartDate().getTime();
            newTemporal.getStartDate().setTime(time);
        }
        if (temporal.getEndDate() != null && temporal.getEndDate().getTime() != null) {
            Time time = temporal.getEndDate().getTime();
            newTemporal.getEndDate().setTime(time);
        }
        return newTemporal;
    }

    private Temporal relativeDayOfWeekInterval(Temporal temporal, LocalDateTime dateTime) {
        Temporal startTemporal = parser.getRelativeTemporalObjectByDayOfWeek(temporal.getStartDate().getDate().getDayOfWeek(), dateTime);
        dateTime = dateTime.withDayOfMonth(startTemporal.getEndDate().getDate().getDay());
        dateTime = dateTime.withMonthOfYear(startTemporal.getEndDate().getDate().getMonth());
        Temporal endTemporal = parser.getRelativeTemporalObjectByDayOfWeek(temporal.getEndDate().getDate().getDayOfWeek(), dateTime);
        if (temporal.getStartDate() != null && temporal.getStartDate().getTime() != null) {
            Time time = temporal.getStartDate().getTime();
            startTemporal.getStartDate().setTime(time);
        }
        if (temporal.getEndDate() != null && temporal.getEndDate().getTime() != null) {
            TimeDate endDate = endTemporal.getEndDate();
            Time time = temporal.getEndDate().getTime();
            endDate.setTime(time);
        }
        startTemporal.setEndDate(endTemporal.getEndDate());
        startTemporal.setRule(temporal.getRule());
        startTemporal.setGroup(temporal.getGroup());
        return startTemporal;
    }

    private Temporal relativeDayOfWeek(Temporal temporal, LocalDateTime dateTime) {

        if (temporal.getStartDate() == null) {
            temporal.setStartDate(temporal.getEndDate());
        } else if (temporal.getStartDate().getTime() == null) {
            temporal.getStartDate().setTime(temporal.getEndDate().getTime());
        }
        Temporal newTemporal = parser.getRelativeTemporalObjectByDayOfWeek(temporal.getStartDate().getDate().getDayOfWeek(), dateTime);
        newTemporal.setRule(temporal.getRule());
        newTemporal.setGroup(temporal.getGroup());
        if (temporal.getStartDate() != null && temporal.getStartDate().getTime() != null) {
            Time time = temporal.getStartDate().getTime();
            newTemporal.getStartDate().setTime(time);
        }
        if (temporal.getEndDate() != null && temporal.getEndDate().getTime() != null) {
            TimeDate endDate = new TimeDate();
            endDate.setDate(newTemporal.getEndDate().getDate());
            Time time = temporal.getEndDate().getTime();
            endDate.setTime(time);
            newTemporal.setEndDate(endDate);
        }
        return newTemporal;

    }

    private TreeSet<TemporalExtraction> relativeDate(List<TemporalExtraction> list, LocalDateTime dateTime) {
        if (dateTime == null) {
            dateTime = new LocalDateTime();
        }
        for (int i = 0; i < list.size(); i++) {
            TemporalExtraction extraction = list.get(i);
            Temporal temporal = extraction.getTemporal().get(0);
            Temporal new_temporal = new Temporal();
            if (temporal.getType() == Type.RELATIVE_TODAY) {
                new_temporal = parser.getRelativeTemporalObjectByProperty(extraction.getTemporalExpression(), dateTime);
                list.get(i).getTemporal().set(0, new_temporal);
                list.get(i).getTemporal().get(0).setType(Type.RELATIVE_TODAY);
            }
            if (temporal.getType() == Type.RELATIVE_DATE) {
                new_temporal = parser.getRelativeDurationDate(extraction.getTemporalExpression(), dateTime);
                list.get(i).getTemporal().set(0, new_temporal);
                list.get(i).getTemporal().get(0).setType(Type.RELATIVE_TODAY);
            }
            if (temporal.getType() == Type.RELATIVE_DATE_ORDER) {
                new_temporal = parser.getRelativeDurationDate2(extraction.getTemporalExpression(), dateTime);
                list.get(i).getTemporal().set(0, new_temporal);
                list.get(i).getTemporal().get(0).setType(Type.RELATIVE_TODAY);
            }
            new_temporal.setGroup(temporal.getGroup());
            new_temporal.setRule(temporal.getRule());
        }
        return new TreeSet<>(list);
    }

}
