package com.codeminders.labs.timeextractor.rules.date;

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
import com.codeminders.labs.timeextractor.temporal.entities.Type;
import com.codeminders.labs.timeextractor.temporal.entities.WeekOfMonth;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;
import com.codeminders.labs.timeextractor.utils.Utils;

// the first Tuesday (of the month)
public class DayOfWeekOrderRule1 extends Rule {

    protected double confidence = 0.9;
    private String rule = "\\b((the[\\s]*)?(" + TemporalConstants.BASIC_ORDER + ")[\\s]*(" + "(" + TemporalConstants.DAY_OF_WEEK + "|" + TemporalConstants.DAY_OF_WEEK_EASY + ")"
            + "[s]?)([\\s]*(of)[\\s]*(the[\\s]*)?(month))?)\\b";
    private int priority = 3;
    protected String example = "the first Tuesday, the third Monday of the month, the last Friday";
    protected UUID id = UUID.fromString("1fde55f7-8fee-485f-8b26-50d60536e528");

    public DayOfWeekOrderRule1() {
    }

    public Type getType() {
        return Type.DAY_OF_WEEK;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);

        DayOfWeek dayOfWeek = TemporalBasicCaseParser.getDayOfWeek(m.group(5));
        WeekOfMonth weekOfMonth = TemporalBasicCaseParser.getWeekOfMonth(m.group(3));
        Date date = new Date();
        date.setDayOfWeek(dayOfWeek);
        date.setWeekOfMonth(weekOfMonth);
        Temporal temporal = TemporalObjectGenerator.generateTemporalDate(type, date);
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

    public void setId(UUID id) {
        this.id = id;
    }
}
