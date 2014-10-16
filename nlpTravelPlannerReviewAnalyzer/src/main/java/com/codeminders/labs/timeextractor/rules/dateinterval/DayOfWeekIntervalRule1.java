package com.codeminders.labs.timeextractor.rules.dateinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import com.codeminders.labs.timeextractor.constants.TemporalConstants;
import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.temporal.entities.Date;
import com.codeminders.labs.timeextractor.temporal.entities.DayOfWeek;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.TimeDate;
import com.codeminders.labs.timeextractor.temporal.entities.Type;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;
import com.codeminders.labs.timeextractor.utils.Utils;

// Monday to Tuesday

public class DayOfWeekIntervalRule1 extends Rule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.7;
    private String rule = "((from[\\s]*)?((" + TemporalConstants.DAY_OF_WEEK + "|" + TemporalConstants.DAY_OF_WEEK_EASY + ")([.])?)[\\s]*(-|to|thru|through|–)[\\s]*(" + TemporalConstants.DAY_OF_WEEK
            + "|" + TemporalConstants.DAY_OF_WEEK_EASY + ")([.])?)";
    private int priority = 4;
    protected String example = "Monday to Tuesday";
    protected UUID id = UUID.fromString("2ced1532-57e4-43ff-9e0a-9935737464bf");

    public DayOfWeekIntervalRule1() {
    }

    @Override
    public Type getType() {
        return Type.DATE_INTERVAL;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);

        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        Date startDate = new Date();
        Date endDate = new Date();

        DayOfWeek startDayOfWeek = TemporalBasicCaseParser.getDayOfWeek(m.group(4));
        DayOfWeek endDayOfWeek = TemporalBasicCaseParser.getDayOfWeek(m.group(9));

        if (startDayOfWeek != null && endDayOfWeek != null) {
            startDate.setDayOfWeek(startDayOfWeek);
            endDate.setDayOfWeek(endDayOfWeek);

        }

        start.setDate(startDate);
        end.setDate(endDate);
        Temporal temporal = TemporalObjectGenerator.generateTemporalTime(Type.DATE_INTERVAL, start, end);
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

    @Override
    public int compareTo(Rule o) {
        return super.compare(this, o);
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
