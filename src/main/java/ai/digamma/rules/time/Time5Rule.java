package ai.digamma.rules.time;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import ai.digamma.constants.TemporalConstants;
import ai.digamma.temporal.entities.Type;
import ai.digamma.utils.TemporalBasicCaseParser;
import ai.digamma.utils.TemporalObjectGenerator;
import ai.digamma.utils.Utils;
import ai.digamma.entities.Rule;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.temporal.entities.Time;

// 2100 CET

public class Time5Rule extends Rule {
    private TemporalBasicCaseParser parser = new TemporalBasicCaseParser();
    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private int priority = 2;
    private String rule = "\\b(at[\\s]*|about[\\s]*|at about[\\s]*)?(([0-9]|0[0-9]|1[0-9]|2[0-3])([0-5][0-9])[\\s]*" + TemporalConstants.TIME_ZONE + ")\\b";
    protected String example = "2100 CET";
    protected UUID id = UUID.fromString("b5366b7a-2cad-488d-9530-db8502618b57");

    public Time5Rule() {
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
        int minute = Integer.parseInt(m.group(4));
        int timezone = 0;
        if (m.group(4) != null) {
            timezone = parser.getTimeZone(m.group(5));
            time.setTimezone(timezone);
        }
        time.setHours(hours);
        time.setMinutes(minute);
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