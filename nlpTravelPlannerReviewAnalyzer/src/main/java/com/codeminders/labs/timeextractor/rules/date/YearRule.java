package com.codeminders.labs.timeextractor.rules.date;

import java.util.ArrayList;
import java.util.List;

import com.codeminders.labs.timeextractor.constants.Constants;
import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Date;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;

// 20XX year rule

public class YearRule extends BaseRule {

    String text;
    String rule = Constants.YEAR;

    protected double confidence;

    public YearRule(String text) {
        this.text = text;
    }

    public List<Temporal> getTemporal() {

        int year = Integer.parseInt(text);
        Date date = new Date();
        date.setYear(year);
        Temporal temporal = TemporalObjectGenerator.generateTemporalObject(type, date);
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);

        return temporalList;
    }

    @Override
    public Type getType() {
        return Type.DATE;
    }

}
