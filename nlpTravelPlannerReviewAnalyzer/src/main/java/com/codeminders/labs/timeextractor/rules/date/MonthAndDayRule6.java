package com.codeminders.labs.timeextractor.rules.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.constants.DayOfWeek;
import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Date;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;

public class MonthAndDayRule6 extends BaseRule {
    private String dayOfWeek;
    private String day;
    private String month;
    private String year;
    private double confidence = 0.8;

    public MonthAndDayRule6(String dayOfWeek, String month, String day, String year) {
        this.dayOfWeek = dayOfWeek;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public Type getType() {
        return Type.DATE;
    }

    @Override
    public List<Temporal> getTemporal() {
        Date date = new Date();

        if (this.month != null) {
            int currentMonth = Integer.parseInt(this.month);
            date.setMonth(currentMonth);

        }
        int day = Integer.parseInt(this.day);
        DayOfWeek dayOfWeek = TemporalBasicCaseParser.getDayOfWeek((this.dayOfWeek));
        int year = 0;
        if (this.year != null) {
            year = Integer.parseInt(this.year.trim());
        }
        date.setYear(year);
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

}
