package com.codeminders.labs.timeextractor.rules.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.constants.MonthOfYear;
import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Date;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;

public class MonthAndDayRule4 extends BaseRule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.83;
    private String month;
    private String day;
    private String year;

    public MonthAndDayRule4(String month, String day, String year) {
        this.month = month;
        this.day = day;
        this.year = year;

    }

    @Override
    public Type getType() {
        return Type.DATE;
    }

    @Override
    public List<Temporal> getTemporal() {
        int month = 0;
        int day = 0;
        int year = 0;

        MonthOfYear currentMonth = TemporalBasicCaseParser.getMonthOfYear(this.month);
        if (currentMonth != null) {
            month = currentMonth.getValue();
        }
        day = Integer.parseInt(this.day);
        if (this.year != null) {
            year = Integer.parseInt(this.year.trim());
        }

        Date date = new Date(year, month, day);
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
}