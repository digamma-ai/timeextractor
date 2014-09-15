package com.codeminders.labs.timeextractor.rules.dateinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.constants.MonthOfYear;
import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Date;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.TimeDate;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;

public class MonthToMonthRule1 extends BaseRule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.83;
    private String month1;
    private String month2;
    private String year;

    public MonthToMonthRule1(String month1, String month2, String year) {
        this.month1 = month1;
        this.month2 = month2;
        this.year = year;
    }

    @Override
    public Type getType() {
        return Type.DATE_INTERVAL;
    }

    @Override
    public List<Temporal> getTemporal() {
        int month1 = 0;
        int month2 = 0;
        int year = 0;
        MonthOfYear monthEnum1 = TemporalBasicCaseParser.getMonthOfYear(this.month1);
        MonthOfYear monthEnum2 = TemporalBasicCaseParser.getMonthOfYear(this.month2);

        if (monthEnum1 != null) {
            month1 = monthEnum1.getValue();
        }
        if (monthEnum2 != null) {
            month2 = monthEnum2.getValue();
        }
        if (this.year != null) {
            year = Integer.parseInt(this.year);
        }
        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        Date startDate = new Date();
        Date endDate = new Date();

        startDate.setMonth(month1);
        startDate.setYear(year);
        endDate.setMonth(month2);
        endDate.setYear(year);

        start.setDate(startDate);
        end.setDate(endDate);

        Temporal temporal = TemporalObjectGenerator.generateTemporalTime(Type.DATE_INTERVAL,start, end);
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
}
