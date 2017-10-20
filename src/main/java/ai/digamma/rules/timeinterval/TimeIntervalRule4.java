package ai.digamma.rules.timeinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import ai.digamma.constants.TemporalConstants;
import ai.digamma.entities.ExtractionRule;
import ai.digamma.temporal.entities.*;
import ai.digamma.utils.Utils;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.utils.TemporalBasicCaseParser;
import ai.digamma.utils.TemporalObjectGenerator;

// by 2100 CET

public class TimeIntervalRule4 extends ExtractionRule {
    private TemporalBasicCaseParser parser;

    protected Locale locale = Locale.US;
    protected double confidence = 0.7;
    private int priority = 4;
    private String rule = "\\b((after|before|until|till|til|by)[\\s]*(2[0-3]|1[0-9]|0[0-9]|[0-9])([0-5][0-9])" + "([\\s]*" + TemporalConstants.TIME_ZONE + "))\\b";
    protected String example = "by 2100 CET";
    protected UUID id = UUID.fromString("b1c02fbc-2f9c-4bfa-8148-946f22193fb6");

    public TimeIntervalRule4() {
        parser = new TemporalBasicCaseParser();
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
        int timezone = 0;
        if (m.group(6) != null) {
            timezone = parser.getTimeZone(m.group(6));
            time.setTimezone(timezone);
        }
        if (m.group(3) != null) {
            time.setHours(Integer.parseInt(m.group(3)));
        }
        if (m.group(4) != null) {
            time.setMinutes(Integer.parseInt(m.group(4)));
        }
        TimeTag tag = TemporalBasicCaseParser.getTimeTag(m.group(1));
        if (tag == TimeTag.AFTER) {
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
