package ai.digamma.rules.timeinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import ai.digamma.entities.ExtractionRule;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.temporal.entities.Time;
import ai.digamma.temporal.entities.TimeDate;
import ai.digamma.temporal.entities.Type;
import ai.digamma.utils.TemporalObjectGenerator;
import ai.digamma.utils.Utils;

// from 10 to 11:00

public class TimeIntervalRule15 extends ExtractionRule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private int priority = 5;
    private String rule = "\\b((from|between)[\\s]*)([01]?[0-9]|2[0-3])[\\s]*(to|and)[\\s]*([01]?[0-9]|2[0-3])[\\s]*[.|:]([0-5][0-9])";
    protected String example = "from 20 to 21.30";
    protected UUID id = UUID.fromString("ee72d702-edee-4431-80f5-15fa34b108bc");

    public TimeIntervalRule15() {

    }

    @Override
    public Type getType() {
        return Type.TIME_INTERVAL;

    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);

        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        Time timeFrom = new Time();
        Time timeTo = new Time();

        Temporal temporal = null;

        if (m.group(3) != null) {
            timeFrom.setHours(Integer.parseInt(m.group(3)));
        }

        if (m.group(5) != null) {
            timeTo.setHours(Integer.parseInt(m.group(5)));

        }

        if (m.group(6) != null) {
            timeTo.setMinutes(Integer.parseInt(m.group(6)));
        }

        start.setTime(timeFrom);
        end.setTime(timeTo);

        temporal = TemporalObjectGenerator.generateTemporalTime(Type.TIME_INTERVAL, start, end);
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);
        return temporalList;

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
