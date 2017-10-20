package ai.digamma.rules.timeofday;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import ai.digamma.constants.TemporalConstants;
import ai.digamma.entities.ExtractionRule;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.temporal.entities.Type;
import ai.digamma.utils.TemporalParser;
import ai.digamma.utils.Utils;

// time of day: morning, evening, etc.

public class TimeOfDayRule extends ExtractionRule {
    private TemporalParser parser;
    private double confidence = 0.9;
    private int priority = 1;
    private String rule = "(\\b" + TemporalConstants.TIME_OF_DAY + "[s]?)([\\s]*hours)?\\b";
    protected String example = "time of day: morning, evening, etc.";
    protected UUID id = UUID.fromString("dd7ef249-9eac-499d-930a-0ee2a0c4b897");

    public TimeOfDayRule() {
        parser = new TemporalParser();
    }

    @Override
    public Type getType() {
        return Type.TIME_INTERVAL_INDIRECT;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        Temporal temporal = parser.getTimeOfDay(m.group(1));
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
    public int compareTo(ExtractionRule o) {
        return super.compare(this, o);
    }

    public UUID getId() {
        return id;
    }
}
