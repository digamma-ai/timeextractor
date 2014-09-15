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

// Sunday 16 2014
public class DayOfWeekRule3 extends BaseRule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.83;
    private String dayOfWeek;
    private String dayOfMonth;
    private String year;

    public DayOfWeekRule3(String dayOfWeek, String dayOfMonth, String year) {
        this.dayOfWeek = dayOfWeek;
        this.dayOfMonth = dayOfMonth;
        this.year = year;
    }

    @Override
    public Type getType() {
        return Type.DATE;
    }

    @Override
    public List<Temporal> getTemporal() {
        DayOfWeek dayOfWeek = null;
        int dayOfMonth = 0;
        int year = 0;

        dayOfWeek = TemporalBasicCaseParser.getDayOfWeek((this.dayOfWeek));
        dayOfMonth = Integer.parseInt(this.dayOfMonth);
        if (this.year != null) {
            year = Integer.parseInt(this.year);
        }
        Date date = new Date();
        date.setDayOfWeek(dayOfWeek);
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
}
