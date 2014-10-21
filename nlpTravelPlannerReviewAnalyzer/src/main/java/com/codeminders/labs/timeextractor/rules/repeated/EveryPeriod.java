package com.codeminders.labs.timeextractor.rules.repeated;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;

import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Type;
import com.codeminders.labs.timeextractor.utils.TemporalParser;
import com.codeminders.labs.timeextractor.utils.Utils;

// every day, every week, every month

public class EveryPeriod extends Rule {
    private TemporalParser parser;
    private double confidence = 0.9;
    private String rule = "\\b((every|each)[\\s]*(day|week|month|year|weekday|weekend))\\b";
    protected String example = "every week, every year, each month, etc.";
    protected UUID id = UUID.fromString("6617d13e-f1cb-475e-8d73-a2cf1a42742e");

    private int priority = 2;
    {
        parser = new TemporalParser();
    }

    public EveryPeriod() {
    }

    @Override
    public Type getType() {
        return Type.SET;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        Temporal temporal = parser.getTemporalForEveryPeriod(m.group(3));
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
