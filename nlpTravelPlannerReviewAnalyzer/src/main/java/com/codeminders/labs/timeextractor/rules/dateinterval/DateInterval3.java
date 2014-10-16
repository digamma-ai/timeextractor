package com.codeminders.labs.timeextractor.rules.dateinterval;

import java.util.ArrayList;
import java.util.List;
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

public class DateInterval3 extends Rule {
    private String rule = "\\b(from)[\\s]*(([1-9])|([1-2][0-9])|([3][0-1]))(th)?[\\s]*(" + TemporalConstants.MONTH_OF_YEAR + "|" + TemporalConstants.MONTH_OF_YEAR_EASY
            + ")[\\s]*([-]|(to)|(until))[\\s]*(([1-9])|([1-2][0-9])|([3][0-1]))(th)?[\\s]*(" + TemporalConstants.MONTH_OF_YEAR + "|" + TemporalConstants.MONTH_OF_YEAR_EASY
            + ")[\\s]*(([12][0-9])\\d\\d)?\\b";
    private double confidence = 0.8;
    private int priority = 5;
    protected String example = "from 22 January to 26 March 2014";
    protected UUID id = UUID.fromString("2c415273-a7fd-4c41-85cb-e07bce9604ba");

    public DateInterval3() {

    }

    @Override
    public Type getType() {
        return Type.DATE_INTERVAL;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        int startDay = Integer.parseInt(m.group(2));
        int endDay = Integer.parseInt(m.group(13));
        int monthFrom = 0;
        int monthTo = 0;

        int year = 0;
        MonthOfYear monthEnumFrom = TemporalBasicCaseParser.getMonthOfYear(m.group(7));
        if (monthEnumFrom != null) {
            monthFrom = monthEnumFrom.getValue();
        }
        MonthOfYear monthEnumTo = TemporalBasicCaseParser.getMonthOfYear(m.group(18));
        if (monthEnumTo != null) {
            monthTo = monthEnumTo.getValue();
        }
        if (m.group(21) != null) {
            year = Integer.parseInt(m.group(21));
        }
        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        Date startDate = new Date();
        Date endDate = new Date();

        startDate.setDay(startDay);
        startDate.setMonth(monthFrom);
        startDate.setYear(year);
        endDate.setDay(endDay);
        endDate.setMonth(monthTo);
        endDate.setYear(year);

        start.setDate(startDate);
        end.setDate(endDate);

        Temporal temporal = TemporalObjectGenerator.generateTemporalTime(Type.DATE_INTERVAL, start, end);
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);
        return temporalList;
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
