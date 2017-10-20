package ai.digamma.rules.time;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import ai.digamma.entities.ExtractionRule;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.temporal.entities.Time;
import ai.digamma.temporal.entities.Type;
import ai.digamma.utils.TemporalObjectGenerator;
import ai.digamma.utils.Utils;

// at 5:30 CET
public class Time2Rule extends ExtractionRule {
    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private int priority = 3;
    private String rule = "(\\b(at[\\s]*|about[\\s]*)?(([01]?[0-9]|2[0-3])[:]([0-5][0-9])([:]([0-5][0-9]))?)" + ")";
    protected String example = "at 5:30 CET";
    protected UUID id = UUID.fromString("620ebf43-c0e1-4497-adb0-f2d722db9e05");

    public Time2Rule() {
    }

    @Override
    public Type getType() {
        return Type.TIME;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        Time time = new Time();
        int hours = Integer.parseInt(m.group(4));
        if (m.group(5) != null) {
            int minutes = Integer.parseInt(m.group(5));
            time.setMinutes(minutes);
        }

        if (m.group(7) != null) {
            int seconds = Integer.parseInt(m.group(7));
            time.setSeconds(seconds);
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
    public int compareTo(ExtractionRule o) {
        return super.compare(this, o);
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