package com.codeminders.labs.timeextractor.rules.dateinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.Type;
import com.codeminders.labs.timeextractor.utils.TemporalParser;

public class SeasonRules extends BaseRule {

    private TemporalParser parser;

    protected Locale locale = Locale.US;
    protected double confidence = 0.83;
    private String season;

    public SeasonRules(String season) {
        parser = new TemporalParser();
        this.season = season;
    }

    @Override
    public Type getType() {
        return Type.DATE_INTERVAL;
    }

    @Override
    public List<Temporal> getTemporal() {
        Temporal temporal = parser.getSeason(this.season, 0);
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
