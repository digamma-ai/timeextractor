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

// the second of December
public class DayOrderAndMonthRule1 extends Rule {

    protected double confidence = 0.99;
    private int priority = 2;
    private String rule = "((the)?[\\s]*)" + TemporalConstants.BASIC_ORDER + "([\\s]*(of)?[\\s]*(the)?[\\s]*)?(" + TemporalConstants.MONTH_OF_YEAR + "|" + TemporalConstants.MONTH_OF_YEAR_EASY
            + ")[,;\\s]?(([12][0-9]\\d\\d))?";

    public DayOrderAndMonthRule1() {

    }

    public Type getType() {
        return Type.DATE;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);

        int dayOfMonth = 0;
        MonthOfYear monthOfYear = null;
        int year = 0;

        monthOfYear = TemporalBasicCaseParser.getMonthOfYear((m.group(8)));
        dayOfMonth = TemporalBasicCaseParser.getDayOfWeekFromOrder((m.group(3)));
        if (m.group(10) != null) {
            year = Integer.parseInt(m.group(10).trim());
        }

        Date date = new Date();
        date.setMonth(monthOfYear.getValue());
        date.setDay(dayOfMonth);
        date.setYear(year);
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

}
