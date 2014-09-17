package com.codeminders.labs.timeextractor.rules.set;

import java.util.ArrayList;
import java.util.List;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.utils.TemporalParser;

public class AllPeriod extends BaseRule {
    private String period;
    private TemporalParser parser;
    {
        parser = new TemporalParser();
    }

    public AllPeriod(String period) {
        this.period = period;
    }

    @Override
    public Type getType() {
        return Type.DURATION;
    }

    @Override
    public List<Temporal> getTemporal() {
        Temporal temporal = parser.getDuration(period, 1);
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);
        return temporalList;
    }
}