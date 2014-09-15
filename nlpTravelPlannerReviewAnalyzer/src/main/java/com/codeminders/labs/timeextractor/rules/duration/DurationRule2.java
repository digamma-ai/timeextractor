package com.codeminders.labs.timeextractor.rules.duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalParser;

public class DurationRule2 extends BaseRule {
    private String durationOrder;
    private String durationPeriod;
    private TemporalParser parser;
    private double confidence = 0.9;

    public DurationRule2(String durationOrder, String durationPeriod) {
        parser = new TemporalParser();
        this.durationOrder = durationOrder;
        this.durationPeriod = durationPeriod;
    }

    @Override
    public Type getType() {
        return Type.DURATION;
    }

    @Override
    public List<Temporal> getTemporal() {
        int durationLength = 0;
        if ((this.durationOrder) != null) {
            durationLength = TemporalBasicCaseParser.getIntFromBasicTerm(this.durationOrder);
        }
        Temporal temporal = parser.getDuration(durationPeriod, durationLength);
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
