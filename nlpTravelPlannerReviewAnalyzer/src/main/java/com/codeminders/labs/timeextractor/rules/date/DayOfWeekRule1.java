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

// Sunday, Sundays, Sun

public class DayOfWeekRule1 extends BaseRule {

    private String dayOfWeek;
    protected Locale locale = Locale.US;
    protected double confidence = 0.83;

    public DayOfWeekRule1(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public Type getType() {
        return Type.DATE;
    }

    @Override
    public List<Temporal> getTemporal() {
        DayOfWeek dayOfWeek = null;

        dayOfWeek = TemporalBasicCaseParser.getDayOfWeek(this.dayOfWeek);
        Date date = new Date();

        if (dayOfWeek != null) {
            date.setDayOfWeek(dayOfWeek);

        }
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
