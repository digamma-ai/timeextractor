package com.codeminders.labs.timeextractor.rules.set;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Type;
import com.codeminders.labs.timeextractor.utils.TemporalParser;
import com.codeminders.labs.timeextractor.utils.Utils;

public class AllPeriod extends Rule {
    private TemporalParser parser;
    private double confidence = 0.9;
    private String rule = "\\b((whole|all|entire)[\\s]*(day|week|month|year))\\b";
    private int priority = 2;
    {
        parser = new TemporalParser();
    }

    public AllPeriod() {
    }

    @Override
    public Type getType() {
        return Type.DURATION;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        Temporal temporal = parser.getDuration(m.group(3), 1);
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
}