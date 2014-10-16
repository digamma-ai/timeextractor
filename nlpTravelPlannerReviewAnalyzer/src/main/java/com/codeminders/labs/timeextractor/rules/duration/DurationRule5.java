package com.codeminders.labs.timeextractor.rules.duration;

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

public class DurationRule5 extends Rule {
    private TemporalParser parser;
    private double confidence = 0.9;
    private String rule = "((lasts|about|past|at least|up to|more than|less than|last)[\\s]*)?" + "(" + TemporalConstants.BASIC_NUMBER_TWENTY_HUNDRED + ")[-\\s]("
            + TemporalConstants.BASIC_NUMBER_ONE_TEN + ")" + "([\\s]*" + TemporalConstants.DURATION + ")";
    private int priority = 4;
    protected String example = "lasts twenty minutes, up to ten hours, etc.";
    protected UUID id = UUID.fromString("a6578914-03f3-4297-b9cd-6486974e1fea");

    public DurationRule5() {
        parser = new TemporalParser();
    }

    @Override
    public Type getType() {
        return Type.DURATION;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        int durationLength = 0;
        if ((m.group(3)) != null) {
            int durationLength1 = TemporalBasicCaseParser.getIntFromBasicTerm(m.group(3).trim());
            int durationLength2 = TemporalBasicCaseParser.getIntFromBasicTerm(m.group(5).trim());
            durationLength = durationLength1 + durationLength2;

        }
        Temporal temporal = parser.getDuration(m.group(8), durationLength);

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
