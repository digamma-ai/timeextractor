package com.codeminders.labs.timeextractor.rules.duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.utils.TemporalParser;

public class DurationRule1 extends BaseRule {
    private TemporalParser parser;
    private String duration;
    private String durationPeriod;
    private double confidence = 0.9;

    public DurationRule1(String duration, String durationPeriod) {
        parser = new TemporalParser();
        this.duration = duration;
        this.durationPeriod = durationPeriod;
    }

    @Override
    public Type getType() {
        return Type.DURATION;
    }

    @Override
    public List<Temporal> getTemporal() {
        int duration = 0;
        if (this.duration != null) {
            duration = Integer.parseInt(this.duration);
        }
        Temporal temporal = parser.getDuration(durationPeriod, duration);
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
