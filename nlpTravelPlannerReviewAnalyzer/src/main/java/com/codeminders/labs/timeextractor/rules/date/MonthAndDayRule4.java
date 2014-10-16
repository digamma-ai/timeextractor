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
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;
import com.codeminders.labs.timeextractor.utils.Utils;

// Fri, 6/27

public class MonthAndDayRule4 extends Rule {
    private double confidence = 0.99;
    private int priority = 4;
    private String rule = "(" + TemporalConstants.DAY_OF_WEEK + "|" + TemporalConstants.DAY_OF_WEEK_EASY
            + ")[,]?[\\s]*\\b(([0]?[1-9])|([1][0-2]))[\\/]\\b(([1-9])|([1-2][0-9])|([3][0-1]))\\b[\\s]*([,])?";
    protected String example = "Fri, 6/27; Saturday 01/10";
    protected UUID id = UUID.fromString("cf400fa9-7306-4e87-8e75-9591ae532555");

    public MonthAndDayRule4() {
    }

    @Override
    public Type getType() {
        return Type.DATE;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        Date date = new Date();

        if (m.group(5) != null) {
            int month = Integer.parseInt(m.group(5));
            date.setMonth(month);

        }
        int day = Integer.parseInt(m.group(7));
        DayOfWeek dayOfWeek = TemporalBasicCaseParser.getDayOfWeek((m.group(1)));

        date.setDay(day);
        date.setDayOfWeek(dayOfWeek);

        Temporal temporal = TemporalObjectGenerator.generateTemporalDate(type, date);
        List<Temporal> result = new ArrayList<Temporal>();
        result.add(temporal);
        return result;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
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

    public void setId(UUID id) {
        this.id = id;
    }

}
