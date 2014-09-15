package com.codeminders.labs.timeextractor.rules.timeinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.utils.TemporalParser;

// time of day: morning, evening, etc.

public class TimeOfDayRule extends BaseRule {
    private String timeOfDay;
    private TemporalParser parser;
    private double confidence = 0.99;

    {
        parser = new TemporalParser();
    }

    public TimeOfDayRule(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    @Override
    public Type getType() {
        return Type.TIME_INTERVAL;
    }

    @Override
    public List<Temporal> getTemporal() {
        Temporal temporal = parser.getTimeOfDay(timeOfDay);
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
