package com.codeminders.labs.timeextractor.rules.season;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Type;
import com.codeminders.labs.timeextractor.utils.TemporalParser;
import com.codeminders.labs.timeextractor.utils.Utils;

public class SeasonRules2 extends Rule {
    private TemporalParser parser;
    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private String rule = "\\b(fall|winter|summer|spring|autumn)[\\s]*([2][0-9]\\d\\d)\\b";
    protected int priority = 2;
    protected String example = "fall 2014, winter 2015";
    protected UUID id = UUID.fromString("ef72a1a4-bd22-4a3c-83ea-8be3c98f0da0");

    public SeasonRules2() {
        parser = new TemporalParser();
    }

    @Override
    public Type getType() {
        return Type.DATE_INTERVAL;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        int year = Integer.parseInt(m.group(2));
        Temporal temporal = parser.getSeason(m.group(1), year);
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