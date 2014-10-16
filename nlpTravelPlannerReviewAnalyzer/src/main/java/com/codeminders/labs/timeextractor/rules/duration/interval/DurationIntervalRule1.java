package com.codeminders.labs.timeextractor.rules.duration.interval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import com.codeminders.labs.timeextractor.constants.TemporalConstants;
import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Type;
import com.codeminders.labs.timeextractor.utils.TemporalParser;
import com.codeminders.labs.timeextractor.utils.Utils;

public class DurationIntervalRule1 extends Rule {
    private TemporalParser parser;
    private double confidence = 0.8;
    private int priority = 6;
    private String rule = "\\b" + "(\\d{1,})[\\s]*([-]|to)[\\s]*(\\d{1,})[\\s]*" + TemporalConstants.DURATION + "\\b";
    protected String example = "10-15 month, 11-20 years";
    protected UUID id = UUID.fromString("b6578914-03f3-4297-b9cd-6486974e1fea");

    public DurationIntervalRule1() {
        parser = new TemporalParser();
    }

    @Override
    public Type getType() {
        return Type.DURATION_INTERVAL;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        int durationFrom = 0;
        int durationTo = 0;

        if (m.group(1) != null) {
            durationFrom = Integer.parseInt(m.group(1));
        }
        if (m.group(3) != null) {
            durationTo = Integer.parseInt(m.group(3));
        }
        Temporal duration1 = parser.getDuration(m.group(4), durationFrom);
        Temporal duration2 = parser.getDuration(m.group(4), durationTo);
        Temporal durationInterval = parser.getDurationInterval(duration1, duration2);
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(durationInterval);
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

    @Override
    public int compareTo(Rule o) {
        return super.compare(this, o);
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public UUID getId() {
        return id;
    }
}
