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
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Time;
import com.codeminders.labs.timeextractor.temporal.entities.TimeDate;
import com.codeminders.labs.timeextractor.temporal.entities.Type;
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
            } else if (temporal.getType() == Type.DAY_OF_WEEK) {
                temporal = parser.getRelativeTemporalObjectByDayOfWeek(temporal.getStartDate().getDate().getDayOfWeek(), dateTime);
                list.get(i).getTemporal().set(0, temporal);
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
                        startTimeDate = summerTime(startTimeDate);
                        startTimeDate = convertDateAndOffset(startTimeDate, offsetTimeZone);
                        temporal.setStartDate(startTimeDate);
                    }
                    TimeDate endTimeDate = temporal.getEndDate();
                    if (endTimeDate != null) {
                        endTimeDate = summerTime(endTimeDate);
                        endTimeDate = convertDateAndOffset(endTimeDate, offsetTimeZone);
                        temporal.setEndDate(endTimeDate);
                    }
                }
            }
        }
        return receivedTemporals;
    }

    private TimeDate summerTime(TimeDate timeDate) {
        if (timeDate == null) {
            return null;
        }

        if (summerTimeIsObserved(timeDate)) {
            LocalDateTime localDateTimeStart = Utils.getTimeDate(timeDate);
            localDateTimeStart = localDateTimeStart.minusMinutes(60);
            timeDate = Utils.getTimeDate(localDateTimeStart, timeDate.getTime().getTimezoneOffset(), timeDate);
        }
        return timeDate;
    }

    private TimeDate convertDateAndOffset(TimeDate timeDate, int offsetTimeZone) {
        if (timeDate == null) {
            return null;
        }
        Time time = timeDate.getTime();
        if (timeDate != null && time != null && time.getTimezoneOffset() == -1000) {
            LocalDateTime localDateTimeStart = Utils.getTimeDate(timeDate);
            localDateTimeStart = localDateTimeStart.plusMinutes(offsetTimeZone);
            timeDate = Utils.getTimeDate(localDateTimeStart, 0, timeDate);
        } else if (timeDate != null && time != null) {
            LocalDateTime localDateTimeStart = Utils.getTimeDate(timeDate);
            localDateTimeStart = localDateTimeStart.plusMinutes(timeDate.getTime().getTimezoneOffset());
            timeDate = Utils.getTimeDate(localDateTimeStart, timeDate.getTime().getTimezoneOffset(), timeDate);
        } else {
            LocalDateTime localDateTimeStart = Utils.getTimeDate(timeDate);
            timeDate = Utils.getTimeDate(localDateTimeStart, offsetTimeZone, timeDate);
            localDateTimeStart = Utils.getTimeDate(timeDate);
            localDateTimeStart = localDateTimeStart.plusMinutes(offsetTimeZone);
            timeDate = Utils.getTimeDate(localDateTimeStart, timeDate.getTime().getTimezoneOffset(), timeDate);
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
