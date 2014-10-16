package com.codeminders.labs.timeextractor.rules.timeinterval;

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

// between 7pm to midnight

public class TimeIntervalRule17 extends Rule {

    private TemporalParser parser;
    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private int priority = 6;
    private String rule = "\\b(from|between)[\\s]*" + "([01]?[0-9]|2[0-3])[\\s]*(([p,P][.]?[m,M][.]?)|([a,A][.]?[m,M]\\.?))[\\s]*(to|and)[\\s]*" + TemporalConstants.TIME_OF_DAY + "\\b";
    protected String example = "from morning to 14pm";
    protected UUID id = UUID.fromString("ae135d69-9fcc-4014-9c1c-f02754be012a");

    public TimeIntervalRule17() {
        parser = new TemporalParser();
    }

    @Override
    public Type getType() {
        return Type.TIME_INTERVAL;

    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        Temporal temporal = parser.getTimeOfDay(m.group(7));
        int hours = Integer.parseInt(m.group(2));
        hours = Utils.convertTime(hours, m.group(3));
        temporal.getStartDate().getTime().setHours(hours);
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);
        return temporalList;

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
