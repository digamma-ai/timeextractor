package com.codeminders.labs.timeextractor.rules.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.codeminders.labs.timeextractor.constants.Constants.*;

import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Date;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.Type;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;

public class MonthAndYearRule1 extends BaseRule {

    public static String rule = "(" + MONTH_OF_YEAR + "|" + MONTH_OF_YEAR_EASY + ")" + "[.]?\\s*([2][0-9]\\d\\d)";
    private String month;
    private String year;

    protected Locale locale = Locale.US;
    protected double confidence = 0.9;

    public MonthAndYearRule1(String month, String year) {
        this.month = month;
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

        month = TemporalBasicCaseParser.getMonthOfYear(this.month).getValue();
        year = Integer.parseInt(this.year);

        Date date = new Date(year, month, 0);
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