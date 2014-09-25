package com.codeminders.labs.timeextractor.rules.timeinterval;

import java.util.ArrayList;
import java.util.List;

import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.Type;
import com.codeminders.labs.timeextractor.utils.TemporalParser;

public class TimeIntervalRule2 extends BaseRule {

    private String timeOfDay1;
    private String timeOfDay2;
    private TemporalParser parser;
    private double confidence = 0.9;

    {
        parser = new TemporalParser();
    }

    public TimeIntervalRule2(String timeOfDay1, String timeOfDay2) {
        this.timeOfDay1 = timeOfDay1;
        this.timeOfDay2 = timeOfDay2;
    }

    @Override
    public Type getType() {
        return Type.TIME_INTERVAL;
    }

    @Override
    public List<Temporal> getTemporal() {
        Temporal temporal = parser.getTimeOfDay(timeOfDay1);
        Temporal temporal2 = parser.getTimeOfDay(timeOfDay2);
        temporal.setEndDate(temporal2.getEndDate());

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