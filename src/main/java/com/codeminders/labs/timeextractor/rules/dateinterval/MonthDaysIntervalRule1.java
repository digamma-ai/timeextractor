package com.codeminders.labs.timeextractor.rules.dateinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import com.codeminders.labs.timeextractor.constants.TemporalConstants;
import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.temporal.entities.Date;
import com.codeminders.labs.timeextractor.temporal.entities.MonthOfYear;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.TimeDate;
import com.codeminders.labs.timeextractor.temporal.entities.Type;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;
import com.codeminders.labs.timeextractor.utils.Utils;

//July 28th-31st (date period ), Jul 28th-31st 2014
public class MonthDaysIntervalRule1 extends Rule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.9;
    private String rule = "\\b((" + TemporalConstants.MONTH_OF_YEAR + "|" + TemporalConstants.MONTH_OF_YEAR_EASY
            + ")[.]?)[\\s]*(([3][0-1]|[1-2][0-9]|[1-9])[\\s]*(th|st|rd|nd)?[\\s]*[-][\\s]*([3][0-1]|[1-2][0-9]|[1-9])(th|st|rd|nd)?)([\\s]*[,]?[\\s]*([2][0-9]\\d\\d))?";
    protected int priority = 6;
    protected String example = "Jul 28th-31st 2014";
    protected UUID id = UUID.fromString("a309c8b4-4200-4776-98be-bbb024102565");

    public MonthDaysIntervalRule1() {

    }

    @Override
    public Type getType() {
        return Type.DATE_INTERVAL;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);

        int startDay = Integer.parseInt(m.group(6));
        int endDay = Integer.parseInt(m.group(8));
        int month = 0;
        int year = 0;
        MonthOfYear monthEnum = TemporalBasicCaseParser.getMonthOfYear(m.group(2));

        if (monthEnum != null) {
            month = monthEnum.getValue();
        }
        if (m.group(11) != null) {
            year = Integer.parseInt(m.group(11));
        }
        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        Date startDate = new Date();
        Date endDate = new Date();

        startDate.setDay(startDay);
        startDate.setMonth(month);
        startDate.setYear(year);
        endDate.setDay(endDay);
        endDate.setMonth(month);
        endDate.setYear(year);

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
