package com.codeminders.labs.timeextractor.rules.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;

import com.codeminders.labs.timeextractor.constants.TemporalConstants;
import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.temporal.entities.Date;
import com.codeminders.labs.timeextractor.temporal.entities.MonthOfYear;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Type;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;
import com.codeminders.labs.timeextractor.utils.Utils;

// Jan-2013

public class MonthAndDayRule6 extends Rule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private int priority = 3;
    private String rule = "(" + TemporalConstants.MONTH_OF_YEAR + "|" + TemporalConstants.MONTH_OF_YEAR_EASY + ")[-]([12][0-9]\\d\\d)";

    public MonthAndDayRule6() {
    }

    public Type getType() {
        return Type.DATE;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);

        MonthOfYear monthOfYear = TemporalBasicCaseParser.getMonthOfYear(m.group(1));
        int month = 0;
        if (monthOfYear != null) {
            month = monthOfYear.getValue();
        }

        int year = 0;
        if (!(m.group(4) == null)) {
            year = Integer.parseInt(m.group(4));
        }

        Date date = new Date(year, month, 0);
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

    @Override
    public int compareTo(Rule o) {
        return super.compare(this, o);
    }

}
