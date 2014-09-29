package com.codeminders.labs.timeextractor.rules.duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;

import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Type;
import com.codeminders.labs.timeextractor.utils.TemporalParser;
import com.codeminders.labs.timeextractor.utils.Utils;

public class DurationRule2 extends Rule {
    private TemporalParser parser;
    private double confidence = 0.9;
    private String rule = "((about|lasts|past|at least|up to|more than)[\\s]*)((an|a)[\\s*])?(hour|minute|second|day|week|month|year)";
    private int priority = 2;

    public DurationRule2() {
        parser = new TemporalParser();
    }

    @Override
    public Type getType() {
        return Type.DURATION;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);

        int duration = 1;
        Temporal temporal = parser.getDuration(m.group(5), duration);
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
}
