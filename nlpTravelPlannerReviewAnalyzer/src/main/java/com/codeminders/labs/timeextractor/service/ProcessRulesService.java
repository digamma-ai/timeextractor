package com.codeminders.labs.timeextractor.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.TreeSet;

import org.joda.time.LocalDateTime;

import com.codeminders.labs.timeextractor.entities.Settings;
import com.codeminders.labs.timeextractor.entities.TemporalExtraction;
import com.codeminders.labs.timeextractor.temporal.entities.DayOfWeek;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Time;
import com.codeminders.labs.timeextractor.temporal.entities.TimeDate;
import com.codeminders.labs.timeextractor.temporal.entities.Type;
import com.codeminders.labs.timeextractor.temporal.entities.WeekOfMonth;
import com.codeminders.labs.timeextractor.utils.TemporalParser;
import com.codeminders.labs.timeextractor.utils.Utils;

/* Class to change timezone and date if null to current */

public class ProcessRulesService {

    private TemporalParser parser = new TemporalParser();

    public TreeSet<TemporalExtraction> processRelativeDate(TreeSet<TemporalExtraction> receivedTemporals, Settings settings) {
        List<TemporalExtraction> list = new ArrayList<TemporalExtraction>(receivedTemporals);
        receivedTemporals = relativeDate(list, settings.getDate());
        return receivedTemporals;
    }

    public TreeSet<TemporalExtraction> processRelativeDayOfWeek(TreeSet<TemporalExtraction> receivedTemporals, Settings settings) {
        List<TemporalExtraction> list = new ArrayList<TemporalExtraction>(receivedTemporals);
        receivedTemporals = dayOfWeekRelative(list, settings.getDate());
        return receivedTemporals;
    }

    public TreeSet<TemporalExtraction> processRelativeDayOfWeekWeekOfMonth(TreeSet<TemporalExtraction> receivedTemporals, Settings settings) {
        List<TemporalExtraction> list = new ArrayList<TemporalExtraction>(receivedTemporals);
        receivedTemporals = dayOfWeekWeekOfMonthRelative(list, settings.getDate());
        return receivedTemporals;
    }

    public TreeSet<TemporalExtraction> processRelativeDayOfWeekInterval(TreeSet<TemporalExtraction> receivedTemporals, Settings settings) {
        List<TemporalExtraction> list = new ArrayList<TemporalExtraction>(receivedTemporals);
        receivedTemporals = dayOfWeekIntervalRelative(list, settings.getDate());
        return receivedTemporals;
    }

    private TreeSet<TemporalExtraction> dayOfWeekIntervalRelative(List<TemporalExtraction> list, LocalDateTime dateTime) {
        if (dateTime == null) {
            dateTime = new LocalDateTime();
        }
        for (int i = 0; i < list.size(); i++) {
            TemporalExtraction extraction = list.get(i);
            Temporal temporal = extraction.getTemporal().get(0);
            if (temporal.getType() == Type.DAY_OF_WEEK_INTERVAL) {
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
                    endDate.setDate(temporal.getEndDate().getDate());
                    Time time = temporal.getEndDate().getTime();
                    endDate.setTime(time);
                }
                startTemporal.setEndDate(endTemporal.getEndDate());
                list.get(i).getTemporal().set(0, startTemporal);
            }
        }
        return new TreeSet<TemporalExtraction>(list);
    }

    private TreeSet<TemporalExtraction> dayOfWeekWeekOfMonthRelative(List<TemporalExtraction> list, LocalDateTime dateTime) {
        if (dateTime == null) {
            dateTime = new LocalDateTime();
        }

        for (int i = 0; i < list.size(); i++) {
            TemporalExtraction extraction = list.get(i);
            Temporal temporal = extraction.getTemporal().get(0);
            if (temporal.getType() == Type.DAY_OF_WEEK_WEEK_OF_MONTH) {
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
                if (temporal.getStartDate() != null && temporal.getStartDate().getTime() != null) {
                    Time time = temporal.getStartDate().getTime();
                    newTemporal.getStartDate().setTime(time);
                }
                if (temporal.getEndDate() != null && temporal.getEndDate().getTime() != null) {
                    Time time = temporal.getEndDate().getTime();
                    newTemporal.getEndDate().setTime(time);
                }
                list.get(i).getTemporal().set(0, newTemporal);
            }
        }
        return new TreeSet<TemporalExtraction>(list);
    }

    private TreeSet<TemporalExtraction> relativeDate(List<TemporalExtraction> list, LocalDateTime dateTime) {
        if (dateTime == null) {
            dateTime = new LocalDateTime();
        }
        for (int i = 0; i < list.size(); i++) {
            TemporalExtraction extraction = list.get(i);
            Temporal temporal = extraction.getTemporal().get(0);
            if (temporal.getType() == Type.RELATIVE_TODAY) {
                temporal = parser.getRelativeTemporalObjectByProperty(extraction.getTemporalExpression(), dateTime);
                list.get(i).getTemporal().set(0, temporal);
                list.get(i).getTemporal().get(0).setType(Type.RELATIVE_TODAY);
            }
        }
        return new TreeSet<TemporalExtraction>(list);
    }

    public TreeSet<TemporalExtraction> dayOfWeekRelative(List<TemporalExtraction> list, LocalDateTime dateTime) {
        if (dateTime == null) {
            dateTime = new LocalDateTime();
        }
        for (int i = 0; i < list.size(); i++) {
            TemporalExtraction extraction = list.get(i);
            Temporal temporal = extraction.getTemporal().get(0);
            if (temporal.getType() == Type.DAY_OF_WEEK) {
                if (temporal.getStartDate() == null) {
                    temporal.setStartDate(temporal.getEndDate());
                } else if (temporal.getStartDate().getTime() == null) {
                    temporal.getStartDate().setTime(temporal.getEndDate().getTime());
                }
                Temporal newTemporal = parser.getRelativeTemporalObjectByDayOfWeek(temporal.getStartDate().getDate().getDayOfWeek(), dateTime);
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
                list.get(i).getTemporal().set(0, newTemporal);
            }
        }
        return new TreeSet<TemporalExtraction>(list);
    }

    public TreeSet<TemporalExtraction> changeRulesAccordingToUserTimeZoneAndCurrentDate(TreeSet<TemporalExtraction> receivedTemporals, Settings settings) {
        Iterator<TemporalExtraction> itr = receivedTemporals.iterator();
        int offsetTimeZone = settings.getTimezoneOffset();
        while (itr.hasNext()) {
            TemporalExtraction te = (TemporalExtraction) itr.next();
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

}
