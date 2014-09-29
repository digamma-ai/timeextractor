package com.codeminders.labs.timeextractor.rules.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;

import com.codeminders.labs.timeextractor.constants.TemporalConstants;
import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.temporal.entities.Date;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Type;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;
import com.codeminders.labs.timeextractor.utils.Utils;

// October 2012  or Oct. 2012
public class MonthAndYearRule1 extends Rule {

    private int priority = 2;
    private String rule = "\\b(" + TemporalConstants.MONTH_OF_YEAR + "|" + TemporalConstants.MONTH_OF_YEAR_EASY + ")" + "[.;,]?\\s*([2][0-9]\\d\\d)\\b";

    protected Locale locale = Locale.US;
    protected double confidence = 0.9;

    public MonthAndYearRule1() {
    }

    @Override
    public Type getType() {
        return Type.DATE;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);

        int month = TemporalBasicCaseParser.getMonthOfYear(m.group(1)).getValue();
        int year = Integer.parseInt(m.group(4));

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