package com.codeminders.labs.timeextractor.rules.timeinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Time;
import com.codeminders.labs.timeextractor.temporal.entities.TimeDate;
import com.codeminders.labs.timeextractor.temporal.entities.Type;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;
import com.codeminders.labs.timeextractor.utils.Utils;

// 9am till 6pm

public class TimeIntervalRule9 extends Rule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private int priority = 5;
    private String rule = "\\b((from|between)[\\s]*)?(([0-9]|0[0-9]|1[0-9]|2[0-3]))[\\s]?(([p,P][.]?[m,M]?)|([a,A][.]?[m,M]?))?[\\s]*(to|till|until|before|-)[\\s]*(([0-9]|0[0-9]|1[0-9]|2[0-3]))[\\s]?(([p,P][.]?[m,M]?)|([a,A][.]?[m,M]?))\\b";
    protected String example = "9am till 6pm";
    protected UUID id = UUID.fromString("730197fe-92c5-4e12-bfba-b13b6335a2a9");

    public TimeIntervalRule9() {

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
            if (m.group(5) != null) {
                timeFrom.setHours(Utils.convertTime(timeFrom.getHours(), m.group(5)));
            }
        }

        if (m.group(9) != null) {
            timeTo.setHours(Integer.parseInt(m.group(9)));
            if (m.group(11) != null) {
                timeTo.setHours(Utils.convertTime(timeTo.getHours(), m.group(11)));
            }

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

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    @Override
    public int compareTo(Rule o) {
        return super.compare(this, o);
    }

    public UUID getId() {
        return id;
    }

}
