package ai.digamma.rules.timeinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import ai.digamma.entities.ExtractionRule;
import ai.digamma.temporal.entities.TimeDate;
import ai.digamma.utils.Utils;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.temporal.entities.Time;
import ai.digamma.temporal.entities.Type;
import ai.digamma.utils.TemporalObjectGenerator;

// 11-5:45
public class TimeIntervalRule12 extends ExtractionRule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private int priority = 4;
    private String rule = "\\b(([01]?[0-9]|2[0-3])[\\s]*[-|to][\\s]*([01]?[0-9]|2[0-3])[:]([0-5][0-9]))\\b";
    protected String example = "11-5:45";
    protected UUID id = UUID.fromString("4b8cd3a0-8352-45c4-95e7-156e26523a61");

    public TimeIntervalRule12() {

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
        if (m.group(2) != null) {
            int hours = Integer.parseInt(m.group(2));
            timeFrom.setHours(hours);

        }

        if (m.group(3) != null) {
            int hours = Integer.parseInt(m.group(3));

            hours = Utils.convertTime(hours, "pm");
            timeTo.setHours(hours);
        }

        if (m.group(4) != null) {
            timeTo.setMinutes(Integer.parseInt(m.group(4)));
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
    public int compareTo(ExtractionRule o) {
        return super.compare(this, o);
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    @Override
    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public UUID getId() {
        return id;
    }
}
