package com.codeminders.labs.timeextractor.rules.duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.temporal.entities.Duration;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Type;
import com.codeminders.labs.timeextractor.utils.Utils;

public class DurationRule4 extends Rule {
    private double confidence = 0.9;
    private String rule = "\\b((lasts|about|past|at least|up to|more than|less than|last)[\\s]*)?" + "([\\d]{1,})[\\s]*(hr|h)[\\s]*([\\d]{1,})[\\s]*(mins|min|mn|m)" + "\\b";
    private int priority = 4;
    protected String example = "lasts 12hr 2 mins, at least 2 mins";
    protected UUID id = UUID.fromString("a2578914-03f3-4297-b9cd-6486974e1fea");

    public DurationRule4() {
    }

    @Override
    public Type getType() {
        return Type.DURATION;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);

        Duration duration = new Duration();
        duration.setHours(Integer.parseInt(m.group(3)));
        duration.setMinutes(Integer.parseInt(m.group(5)));
        Temporal temporal = new Temporal();
        temporal.setDuration(duration);
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
