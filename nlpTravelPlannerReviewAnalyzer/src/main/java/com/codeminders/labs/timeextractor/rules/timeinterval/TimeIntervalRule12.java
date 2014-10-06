package com.codeminders.labs.timeextractor.rules.timeinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;

import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Time;
import com.codeminders.labs.timeextractor.temporal.entities.TimeDate;
import com.codeminders.labs.timeextractor.temporal.entities.Type;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;
import com.codeminders.labs.timeextractor.utils.Utils;

// 11-5:45
public class TimeIntervalRule12 extends Rule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private int priority = 4;
    private String rule = "\\b(([01]?[0-9]|2[0-3])[\\s]*[-|to][\\s]*([01]?[0-9]|2[0-3])[:]([0-5][0-9]))\\b";

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
    public int compareTo(Rule o) {
        return super.compare(this, o);
    }

}
