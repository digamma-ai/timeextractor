package com.codeminders.labs.timeextractor.rules.dateinterval;

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
import com.codeminders.labs.timeextractor.utils.TemporalParser;
import com.codeminders.labs.timeextractor.utils.Utils;

public class MonthToMonthInterval extends Rule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.6;
    private String rule = "\\b((between|from)[\\s]*(" + TemporalConstants.MONTH_OF_YEAR + "|" + TemporalConstants.MONTH_OF_YEAR_EASY + "|" + TemporalConstants.SEASON + ")([\\s]*(and|to)[\\s]*)("
            + TemporalConstants.MONTH_OF_YEAR + "|" + TemporalConstants.MONTH_OF_YEAR_EASY + "|" + TemporalConstants.SEASON + "))";
    protected int priority = 6;
    private TemporalParser parser;

    public MonthToMonthInterval() {
        parser = new TemporalParser();
    }

    @Override
    public Type getType() {
        return Type.DATE_INTERVAL;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        String m1 = m.group(3);
        String m2 = m.group(9);
        Temporal temporalStart = new Temporal();
        Temporal temporalEnd = new Temporal();

        if (TemporalConstants.SEASON.contains(m1.toLowerCase())) {
            temporalStart = parser.getSeason(m1, 0);
        } else {
            MonthOfYear monthStartEnum = TemporalBasicCaseParser.getMonthOfYear(m1);
            if (monthStartEnum != null) {
                int monthStart = monthStartEnum.getValue();
                Date date = new Date(0, monthStart, 0);
                temporalStart = TemporalObjectGenerator.generateTemporalDate(type, date);
            }
        }
        if (TemporalConstants.SEASON.contains(m2.toLowerCase())) {
            temporalEnd = parser.getSeason(m2, 0);
        } else {
            MonthOfYear monthEndEnum = TemporalBasicCaseParser.getMonthOfYear(m2);
            if (monthEndEnum != null) {
                int monthEnd = monthEndEnum.getValue();
                Date date = new Date(0, monthEnd, 0);
                temporalEnd = TemporalObjectGenerator.generateTemporalDate(type, date);
            }
        }
        temporalEnd.getStartDate().setDate(temporalStart.getStartDate().getDate());
        List<Temporal> result = new ArrayList<Temporal>();
        result.add(temporalEnd);
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

}
