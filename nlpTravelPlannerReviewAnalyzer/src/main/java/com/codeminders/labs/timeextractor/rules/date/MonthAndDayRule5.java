package com.codeminders.labs.timeextractor.rules.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.constants.DayOfWeek;
import com.codeminders.labs.timeextractor.constants.MonthOfYear;
import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Date;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;

// Sunday 17 of July

public class MonthAndDayRule5 extends BaseRule {
    private String dayOfWeek;
    private String day;
    private String month;
    private double confidence = 0.99;

    public MonthAndDayRule5(String dayOfWeek, String day, String month) {
        this.dayOfWeek = dayOfWeek;
        this.day = day;
        this.month = month;
    }

    @Override
    public Type getType() {
        return Type.DATE;
    }

    @Override
    public List<Temporal> getTemporal() {
        Date date = new Date();

        if (this.month != null) {
            MonthOfYear currentMonth = TemporalBasicCaseParser.getMonthOfYear(this.month);
            date.setMonth(currentMonth.getValue());

        }
        int day = Integer.parseInt(this.day);
        DayOfWeek dayOfWeek = TemporalBasicCaseParser.getDayOfWeek((this.dayOfWeek));

        date.setDay(day);
        date.setDayOfWeek(dayOfWeek);

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
