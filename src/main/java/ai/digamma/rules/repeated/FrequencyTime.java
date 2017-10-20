package ai.digamma.rules.repeated;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;

import ai.digamma.entities.ExtractionRule;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.temporal.entities.Type;
import ai.digamma.utils.TemporalParser;
import ai.digamma.utils.Utils;

public class FrequencyTime extends ExtractionRule {

    private double confidence = 0.9;
    private TemporalParser parser;
    private String rule = "\\b(daily|weekly|monthly|yearly|annual|hourly)\\b";
    protected String example = "daily, weekly, monthly, annualy, hourly, etc.";
    protected UUID id = UUID.fromString("74bd1c6b-80d3-4f57-86b2-25cb6e4f3e08");

    private int priority = 2;
    {
        parser = new TemporalParser();
    }

    public FrequencyTime() {

    }

    @Override
    public Type getType() {
        return Type.PERIODIC;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        Temporal temporal = parser.getTemporalForEveryPeriod(m.group());
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
    public int compareTo(ExtractionRule o) {
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
