package ai.digamma.rules.duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import ai.digamma.constants.TemporalConstants;
import ai.digamma.entities.Rule;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.temporal.entities.Type;
import ai.digamma.utils.TemporalBasicCaseParser;
import ai.digamma.utils.TemporalParser;
import ai.digamma.utils.Utils;

public class DurationRule3 extends Rule {
    private TemporalParser parser;
    private double confidence = 0.9;
    private String rule = "\\b((lasts|about|past|at least|up to|more than|less than|last)[\\s]*)?" + "(" + TemporalConstants.BASIC_NUMBER_ONE_TEN + "|" + TemporalConstants.BASIC_NUMBER_TWENTY_HUNDRED
            + "|" + TemporalConstants.BASIC_NUMBER_ELEVEN_NINETEEN + ")([\\s]*" + TemporalConstants.DURATION + ")\\b";
    private int priority = 3;
    protected String example = "lasts elevent days, more than three hours, etc.";
    protected UUID id = UUID.fromString("a2578914-03f3-4297-b9cd-6486974e1feb");

    public DurationRule3() {
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
            durationLength = TemporalBasicCaseParser.getIntFromBasicTerm(m.group(3).trim());
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
