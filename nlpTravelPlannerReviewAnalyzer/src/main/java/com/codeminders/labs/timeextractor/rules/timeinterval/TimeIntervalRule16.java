package com.codeminders.labs.timeextractor.rules.timeinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;

import com.codeminders.labs.timeextractor.constants.TemporalConstants;
import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Type;
import com.codeminders.labs.timeextractor.utils.TemporalParser;
import com.codeminders.labs.timeextractor.utils.Utils;

// between noon and 3pm

public class TimeIntervalRule16 extends Rule {

    private TemporalParser parser;
    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private int priority = 6;
    private String rule = "\\b(from|between)[\\s]*" + TemporalConstants.TIME_OF_DAY + "[\\s]*(to|and)[\\s]*([01]?[0-9]|2[0-3])[\\s]*(([p,P][.]?[m,M][.]?)|([a,A][.]?[m,M]\\.?))(?!,\\S)";

    {
        parser = new TemporalParser();
    }

    public TimeIntervalRule16() {

    }

    @Override
    public Type getType() {
        return Type.TIME_INTERVAL;

    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);

        Temporal temporal = parser.getTimeOfDay(m.group(2));
        int hours = Integer.parseInt(m.group(5));
        hours = Utils.convertTime(hours, m.group(6));
        temporal.getEndDate().getTime().setHours(hours);
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
