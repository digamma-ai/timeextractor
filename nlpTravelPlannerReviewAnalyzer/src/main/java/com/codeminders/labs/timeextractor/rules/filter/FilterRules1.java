package com.codeminders.labs.timeextractor.rules.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Type;

public class FilterRules1 extends Rule {
    private double confidence = 0.9;
    private String rule = "\\b((the sun)|(may be)|(may not be)|(spring fall)|(wall fall)|(water fall)|(\\d[.]\\d[%]))|(by[\\s]*[1-9][1-9]?[.][1-9][\\s]*([%]|(percent)))\\b";
    protected String example = "the sun, may be, 1.4%, water fall";
    protected UUID id = UUID.fromString("8b486001-fcaf-4379-a604-6c74678522a7");
    private int priority = 20;

    public FilterRules1() {
    }

    @Override
    public Type getType() {
        return Type.Filter;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        List<Temporal> temporalList = new ArrayList<Temporal>();
        Temporal temporal = new Temporal();
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