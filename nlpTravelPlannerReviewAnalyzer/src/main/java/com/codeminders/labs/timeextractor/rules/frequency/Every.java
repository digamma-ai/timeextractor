package com.codeminders.labs.timeextractor.rules.frequency;

import java.util.ArrayList;
import java.util.List;

import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Type;

public class Every extends Rule {

    private double confidence = 0.4;
    private String rule = "\\b(every|each)\\b";
    private int priority = 2;

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
}
