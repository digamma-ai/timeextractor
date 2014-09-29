package com.codeminders.labs.timeextractor.rules.timeinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;

import com.codeminders.labs.timeextractor.constants.TemporalConstants;
import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Time;
import com.codeminders.labs.timeextractor.temporal.entities.TimeDate;
import com.codeminders.labs.timeextractor.temporal.entities.Type;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;
import com.codeminders.labs.timeextractor.utils.Utils;

// from 10:00 to 11:00
//from 10.00 to 11.00

public class TimeIntervalRule10 extends Rule {
    private TemporalBasicCaseParser parser;

    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private int priority = 5;
    private String rule = "\\b((from|between)[\\s]*)([01]?[0-9]|2[0-3])[\\s]*[.|:]([0-5][0-9])[\\s]*(to|and)[\\s]*([01]?[0-9]|2[0-3])[\\s]*[.|:]([0-5][0-9])" + "([\\s]*" + TemporalConstants.TIME_ZONE
            + ")?";
    {
        parser = new TemporalBasicCaseParser();
    }

    public TimeIntervalRule10() {

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
        int timezone = 0;

        if (m.group(3) != null) {
            timeFrom.setHours(Integer.parseInt(m.group(3)));
            if (m.group(4) != null) {
                timeFrom.setHours(Utils.convertTime(timeFrom.getHours(), m.group(4)));
            }
        }

        if (m.group(6) != null) {
            timeTo.setHours(Integer.parseInt(m.group(6)));
            if (m.group(7) != null) {
                timeTo.setHours(Utils.convertTime(timeTo.getHours(), m.group(7)));
            }

        }

        if (m.group(12) != null) {
            timezone = parser.getTimeZone(m.group(12));
            timeTo.setTimezone(timezone);
            timeFrom.setTimezone(timezone);
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
