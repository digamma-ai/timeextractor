package com.codeminders.labs.timeextractor.rules.time;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;

import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Time;
import com.codeminders.labs.timeextractor.temporal.entities.Type;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;
import com.codeminders.labs.timeextractor.utils.Utils;

// at 5:30 pm
public class Time3Rule extends Rule {
    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private int priority = 3;
    private String rule = "\\b(at[\\s]*|about[\\s]*|at about[\\s]*)?(([01]?[0-9]|2[0-3])[\\s]*(([:.,]?)([0-5][0-9]))?)[\\s]*(([p,P][.]?[m,M][.]?)|([a,A][.]?[m,M][.]?)\\b)";

    public Time3Rule() {
    }

    @Override
    public Type getType() {
        return Type.TIME;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        Time time = new Time();
        int hours = Integer.parseInt(m.group(3));
        hours = Utils.convertTime(hours, m.group(7));
        if (m.group(6) != null) {
            int minutes = Integer.parseInt(m.group(6));
            time.setMinutes(minutes);
        }
        time.setHours(hours);
        Temporal temporal = TemporalObjectGenerator.generateTemporalTime(type, time);
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

    @Override
    public int compareTo(Rule o) {
        return super.compare(this, o);
    }

}