package com.codeminders.labs.timeextractor.rules.repeated;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Type;

public class Every extends Rule {

    private double confidence = 0.2;
    private String rule = "\\b(every|each)\\b";
    private int priority = 2;
    protected String example = "every/each (rule is used only for composite rules, not as a simple rule )";
    protected UUID id = UUID.fromString("a69ee2a4-64fe-431a-9931-ddfa7df9a43d");

    public Every() {
    }

    @Override
    public Type getType() {
        return Type.EVERY;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Temporal temporal = new Temporal();
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
