package com.codeminders.labs.timeextractor.rules.date.relative;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import com.codeminders.labs.timeextractor.constants.TemporalConstants;
import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Type;

public class NTimeAgoRule extends Rule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.7;
    private int priority = 5;
    protected String rule = "\\b([\\d]{1,})[\\s]*(" + TemporalConstants.DURATION + ")[\\s]*(ago)\\b";
    protected String example = "10 month ago, 11 years ago, 123 minutes ago, etc.";
    protected UUID id = UUID.fromString("f4d08326-7301-4f3d-8885-62ba81a521cf");

    public NTimeAgoRule() {
    }

    @Override
    public Type getType() {
        return Type.RELATIVE_DATE;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        List<Temporal> results = new ArrayList<Temporal>();
        Temporal temporal = new Temporal();
        results.add(temporal);
        return results;

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
