package com.codeminders.labs.timeextractor.rules.date.relative;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import com.codeminders.labs.timeextractor.constants.TemporalConstants;
import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Type;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalParser;
import com.codeminders.labs.timeextractor.utils.Utils;

public class NTimeAgoRule2 extends Rule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.7;
    private int priority = 5;
    protected String rule = "\\b" + "((" + TemporalConstants.BASIC_NUMBER_ONE_TEN + "|" + "([\\s]*" + TemporalConstants.BASIC_NUMBER_TWENTY_HUNDRED + ")" + "|" + "([\\s]*"
            + TemporalConstants.BASIC_NUMBER_ELEVEN_NINETEEN + "))[\\s]*((" + TemporalConstants.DURATION + "))[\\s]*(ago))\\b";
    private TemporalParser parser;
    protected String example = "one hour ago, two weeks ago, month ago";
    protected UUID id = UUID.fromString("74b57dd8-ccd7-40c5-b1b5-cff2ad657b88");

    public NTimeAgoRule2() {
        parser = new TemporalParser();
    }

    @Override
    public Type getType() {
        return Type.RELATIVE_DATE;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        int length = TemporalBasicCaseParser.getIntFromBasicTerm(m.group(2));
        Temporal temporal = parser.getRelativeDurationDate(m.group(8), length, null);
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

    public void setId(UUID id) {
        this.id = id;
    }
}
