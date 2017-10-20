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
import ai.digamma.temporal.entities.TimeTag;
import ai.digamma.temporal.entities.Type;
import ai.digamma.utils.TemporalBasicCaseParser;
import ai.digamma.utils.TemporalObjectGenerator;
import ai.digamma.utils.Utils;

public class TimeIntervalRule3 extends ExtractionRule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.3;
    private int priority = 4;
    private String rule = "\\b((after|before|until|till|til|by)[\\s]*([01]?[0-9]|2[0-3])([\\s]*[.|:]([0-5][0-9]))?)\\b";
    protected String example = "after 11.30, till 20:00, before 11.20";
    protected UUID id = UUID.fromString("1933be3e-1d4f-46f4-b7e0-a25a4916b903");

    public TimeIntervalRule3() {

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
        Time time = new Time();
        Temporal temporal = null;
        if (m.group(3) != null) {
            time.setHours(Integer.parseInt(m.group(3)));
        }
        if (m.group(5) != null) {
            time.setMinutes(Integer.parseInt(m.group(5)));
        }
        TimeTag tag = TemporalBasicCaseParser.getTimeTag(m.group(1));
        if (tag == TimeTag.BEFORE) {
            start.setTime(time);
            temporal = TemporalObjectGenerator.generateTemporalTime(Type.TIME_INTERVAL, start, null);
        } else {
            end.setTime(time);
            temporal = TemporalObjectGenerator.generateTemporalTime(Type.TIME_INTERVAL, null, end);
        }
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

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    @Override
    public int compareTo(ExtractionRule o) {
        return super.compare(this, o);
    }

    public UUID getId() {
        return id;
    }

}
