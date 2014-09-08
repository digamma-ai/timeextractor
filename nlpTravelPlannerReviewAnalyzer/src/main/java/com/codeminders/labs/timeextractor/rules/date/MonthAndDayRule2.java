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

// 14 July 2012
public class MonthAndDayRule2 extends BaseRule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.83;
    private String month;
    private String day;
    private String year;

    public MonthAndDayRule2(String day, String month, String year) {
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
        System.out.println(this.month);
        int month = 0;
        int day = 0;
        int year = 0;
        day = Integer.parseInt(this.day);
        MonthOfYear monthOfYear = TemporalBasicCaseParser.getMonthOfYear(this.month);
        if (monthOfYear != null) {
            month = monthOfYear.getValue();
        }

        if (!(this.year == null)) {
            year = Integer.parseInt(this.year);
        }

        Date date = new Date(year, month, day);
        Temporal temporal = TemporalObjectGenerator.generateTemporalDate(type, date);

        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);

        return temporalList;
    }
}
