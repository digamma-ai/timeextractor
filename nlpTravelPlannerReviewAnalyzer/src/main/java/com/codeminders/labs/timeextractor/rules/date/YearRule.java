package com.codeminders.labs.timeextractor.rules.date;

import java.util.ArrayList;
import java.util.List;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Date;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;

// 20XX year rule

public class YearRule extends BaseRule {

    String year;

    protected double confidence;

    public YearRule(String year) {
        this.year = year;
    }

    public List<Temporal> getTemporal() {

        Date date = new Date();

        date.setYear(Integer.parseInt(year));
        Temporal temporal = TemporalObjectGenerator.generateTemporalDate(type, date);

        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);

        return temporalList;
    }

    @Override
    public Type getType() {
        return Type.DATE;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

}
