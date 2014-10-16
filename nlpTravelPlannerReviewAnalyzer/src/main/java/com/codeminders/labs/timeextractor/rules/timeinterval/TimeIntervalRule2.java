package com.codeminders.labs.timeextractor.rules.timeinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;

import com.codeminders.labs.timeextractor.constants.TemporalConstants;
import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Type;
import com.codeminders.labs.timeextractor.utils.TemporalParser;
import com.codeminders.labs.timeextractor.utils.Utils;

public class TimeIntervalRule2 extends Rule {

    private TemporalParser parser;
    private int priority = 2;
    private String rule = "\\b(between)[\\s]*" + TemporalConstants.TIME_OF_DAY + "[\\s]*((and|to|until|til)|[-])[\\s]*" + TemporalConstants.TIME_OF_DAY + "\\b";
    protected String example = "between morning and evening";
    protected UUID id = UUID.fromString("db01b0a4-8ef9-49b7-bf17-509533ef7e84");

    private double confidence = 0.9;

    public TimeIntervalRule2() {
        parser = new TemporalParser();
    }

    @Override
    public Type getType() {
        return Type.TIME_INTERVAL;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        Temporal temporal = parser.getTimeOfDay(m.group(2));
        Temporal temporal2 = parser.getTimeOfDay(m.group(6));
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

    @Override
    public int compareTo(Rule o) {
        return super.compare(this, o);
    }

    public UUID getId() {
        return id;
    }

}