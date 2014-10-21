package com.codeminders.labs.timeextractor.rules.weekend;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.temporal.entities.Date;
import com.codeminders.labs.timeextractor.temporal.entities.DayOfWeek;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.TimeDate;
import com.codeminders.labs.timeextractor.temporal.entities.Type;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;

public class WeekEnd extends Rule {

    private double confidence = 0.9;
    private String rule = "\\b(weekend|weekends|week-end|week-ends)\\b";
    protected int priority = 1;
    protected String example = "weekend, week-end, etc.";
    protected UUID id = UUID.fromString("7bf02d24-c82b-47db-99b8-9344f9bbed20");

    public WeekEnd() {
    }

    @Override
    public Type getType() {
        return Type.DAY_OF_WEEK_INTERVAL;

    }

    @Override
    public List<Temporal> getTemporal(String text) {
        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        Date startDate = new Date();
        Date endDate = new Date();

        startDate.setDayOfWeek(DayOfWeek.SA);
        endDate.setDayOfWeek(DayOfWeek.SU);

        start.setDate(startDate);
        end.setDate(endDate);

        Temporal temporal = TemporalObjectGenerator.generateTemporalTime(Type.DATE_INTERVAL, start, end);
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
