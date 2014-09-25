package com.codeminders.labs.timeextractor.rules.timeinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.Time;
import com.codeminders.labs.timeextractor.temporal.entites.TimeDate;
import com.codeminders.labs.timeextractor.temporal.entites.TimeTag;
import com.codeminders.labs.timeextractor.temporal.entites.Type;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;
import com.codeminders.labs.timeextractor.utils.TimeConvertor;

public class TimeIntervalRule4 extends BaseRule {
    private TemporalBasicCaseParser parser;

    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private String hours;
    private String minutes;
    private String pMAm;
    private String timezone;
    private String tag;

    {
        parser = new TemporalBasicCaseParser();
    }

    public TimeIntervalRule4(String tag, String hours, String minutes, String pMAm, String timezone) {
        this.tag = tag;
        this.hours = hours;
        this.minutes = minutes;
        this.pMAm = pMAm;
        this.timezone = timezone;
    }

    public TimeIntervalRule4(String tag, String hours, String pMAm, String timezone) {
        this.tag = tag;
        this.hours = hours;
        this.pMAm = pMAm;
        this.timezone = timezone;
    }

    @Override
    public Type getType() {
        return Type.TIME_INTERVAL;

    }

    @Override
    public List<Temporal> getTemporal() {
        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();
        Time time = new Time();
        Temporal temporal = null;
        int timezone = 0;
        if (this.timezone != null) {
            timezone = parser.getTimeZone(this.timezone);
            time.setTimezone(timezone);
        }
        if (this.hours != null) {
            int hours = Integer.parseInt(this.hours);
            hours = TimeConvertor.convertTime(hours, pMAm);
            time.setHours(hours);
        }
        if (this.minutes != null) {
            time.setMinutes(Integer.parseInt(this.minutes));
        }
        TimeTag tag = TemporalBasicCaseParser.getTimeTag(this.tag);
        if (tag == tag.AFTER) {
            start.setTime(time);
            temporal = TemporalObjectGenerator.generateTemporalTime(Type.TIME_INTERVAL, start, null);
        } else {
            end.setTime(time);
            temporal = TemporalObjectGenerator.generateTemporalTime(Type.TIME_INTERVAL, null, end);
        }
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);
        return temporalList;
    }

    @Override
    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

}
