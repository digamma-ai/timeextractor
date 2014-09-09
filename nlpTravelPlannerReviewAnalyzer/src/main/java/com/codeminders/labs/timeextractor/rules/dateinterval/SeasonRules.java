package com.codeminders.labs.timeextractor.rules.dateinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;

public class SeasonRules extends BaseRule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.83;
    private String season;

    public SeasonRules(String season) {
        this.season = season;
    }

    @Override
    public Type getType() {
        return Type.DATEINTERVAL;
    }

    @Override
    public List<Temporal> getTemporal() {
        Temporal temporal = TemporalBasicCaseParser.getSeason(this.season, 0);
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);
        return temporalList;
    }
}
