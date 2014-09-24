package com.codeminders.labs.timeextractor.rules.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Date;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.Type;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;

public class MonthDayYearRule1 extends BaseRule {

    private String month;
    private String day;
    private String year;

    public MonthDayYearRule1(String day, String month, String year) {
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
        int day = Integer.parseInt(this.day);
        int month = Integer.parseInt(this.month);
        int year = 0;
        if (this.year.length() == 4) {
            year = Integer.parseInt(this.year);
        }
        if (this.year.length() == 2) {
            year = Integer.parseInt("20" + this.year);
        }
        Date date = new Date();
        date.setDay(day);
        date.setMonth(month);
        date.setYear(year);
        Temporal temporal = TemporalObjectGenerator.generateTemporalDate(type, date);

        List<Temporal> result = new ArrayList();
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
