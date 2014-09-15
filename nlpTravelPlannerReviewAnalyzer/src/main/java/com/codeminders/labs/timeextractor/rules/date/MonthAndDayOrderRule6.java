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

public class MonthAndDayOrderRule6 extends BaseRule {
    private String year;
    private String orderDay;
    private String month;
    private double confidence = 0.99;

    public MonthAndDayOrderRule6(String month, String orderDay, String year) {
        this.month = month;
        this.orderDay = orderDay;
        this.year = year;
    }

    @Override
    public Type getType() {
        return Type.DATE;
    }

    @Override
    public List<Temporal> getTemporal() {
        Date date = new Date();

        if (this.year != null) {
            date.setYear(Integer.parseInt(this.year));
        }
        if (this.month != null) {
            MonthOfYear currentMonth = TemporalBasicCaseParser.getMonthOfYear(this.month);
            date.setMonth(currentMonth.getValue());

        }

        int day = TemporalBasicCaseParser.getDayOfWeekFromOrder(this.orderDay);
        date.setDay(day);
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

}