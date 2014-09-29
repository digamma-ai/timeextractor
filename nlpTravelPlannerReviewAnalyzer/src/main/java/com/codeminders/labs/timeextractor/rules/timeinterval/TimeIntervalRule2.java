package com.codeminders.labs.timeextractor.rules.timeinterval;

import java.util.ArrayList;
import java.util.List;
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

    private double confidence = 0.9;

    {
        parser = new TemporalParser();
    }

    public TimeIntervalRule2() {
    }

    @Override
    public Type getType() {
        return Type.TIME_INTERVAL;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        Temporal temporal = parser.getTimeOfDay(m.group(2));
        Temporal temporal2 = parser.getTimeOfDay(m.group(5));
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

    @Override
    public int compareTo(Rule o) {
        return super.compare(this, o);
    }
}