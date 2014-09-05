package com.codeminders.labs.timeextractor.rules.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.constants.Constants;
import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Date;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.TimeDate;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;

// November, Devember, etc.

public class MonthOfYear1 extends BaseRule {

    public static String rule = "\b" + Constants.MONTH_OF_YEAR + "\b";
    private String text;
    protected Locale locale = Locale.US;
    protected double confidence = 0.83;

    public MonthOfYear1(String text) {
        this.text = text;
    }

    @Override
    public Type getType() {
        return Type.DATE;
    }

    @Override
    public List<Temporal> getTemporal() {
        int month = TemporalBasicCaseParser.getMonthOfYear(text).getValue();
        Date date = new Date();
        date.setMonth(month);
        Temporal temporal = TemporalObjectGenerator.generateTemporalObject(type, date);

        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);

        return temporalList;
    }
}
