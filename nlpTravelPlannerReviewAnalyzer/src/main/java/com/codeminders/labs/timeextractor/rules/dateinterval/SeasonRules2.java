package com.codeminders.labs.timeextractor.rules.dateinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;

public class SeasonRules2 extends BaseRule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.83;
    private String season;
    private String year;

    public SeasonRules2(String season, String year) {
        this.season = season;
        this.year = year;
    }

    @Override
    public Type getType() {
        return Type.DATEINTERVAL;
    }

    @Override
    public List<Temporal> getTemporal() {
        int year = Integer.parseInt(this.year);
        Temporal temporal = TemporalBasicCaseParser.getSeason(this.season, year);
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);
        return temporalList;
    }
}
