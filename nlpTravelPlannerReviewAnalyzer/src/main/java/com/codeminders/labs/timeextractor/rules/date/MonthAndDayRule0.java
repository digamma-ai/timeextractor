package com.codeminders.labs.timeextractor.rules.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Date;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;

public class MonthAndDayRule0 extends BaseRule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.83;
    private String month;
    private String day;
    private String year;

    public MonthAndDayRule0(String month, String day, String year) {
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
        int year = 0;
        int month = 0;
        int day = 0;
        month = TemporalBasicCaseParser.getMonthOfYear(this.month).getValue();

        day = Integer.parseInt(this.day);
        if (this.year != null) {
            year = Integer.parseInt(this.year);
        }
        Date date = new Date(year, month, day);
        Temporal temporal = TemporalObjectGenerator.generateTemporalDate(type, date);
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);

        return temporalList;
    }

}