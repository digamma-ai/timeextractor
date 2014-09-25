package com.codeminders.labs.timeextractor.rules.time;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.Time;
import com.codeminders.labs.timeextractor.temporal.entites.Type;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;
import com.codeminders.labs.timeextractor.utils.TimeConvertor;

public class Time1Rule extends BaseRule {
    private TemporalBasicCaseParser parser;
    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private String hours;
    private String aMPm;
    private String timezone;

    public Time1Rule(String hours, String aMPm, String timezone) {
        parser = new TemporalBasicCaseParser();
        this.hours = hours;
        this.aMPm = aMPm;
        this.timezone = timezone;
    }

    @Override
    public Type getType() {
        return Type.TIME;
    }

    @Override
    public List<Temporal> getTemporal() {
        Time time = new Time();
        int hours = Integer.parseInt(this.hours);
        hours = TimeConvertor.convertTime(hours, aMPm);
        time.setHours(hours);
        int timezone = 0;
        if (this.timezone != null) {
            timezone = parser.getTimeZone(this.timezone);
            time.setTimezone(timezone);
        }
        Temporal temporal = TemporalObjectGenerator.generateTemporalTime(type, time);
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);
        return temporalList;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

}