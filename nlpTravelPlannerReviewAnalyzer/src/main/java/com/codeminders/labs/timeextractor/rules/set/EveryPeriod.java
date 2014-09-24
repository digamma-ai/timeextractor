package com.codeminders.labs.timeextractor.rules.set;

import java.util.ArrayList;
import java.util.List;

import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.Type;
import com.codeminders.labs.timeextractor.utils.TemporalParser;

// every day, every week, every month
public class EveryPeriod extends BaseRule {
    private String period;
    private TemporalParser parser;
    {
        parser = new TemporalParser();
    }

    public EveryPeriod(String period) {
        this.period = period;
    }

    @Override
    public Type getType() {
        return Type.SET;
    }

    @Override
    public List<Temporal> getTemporal() {
        Temporal temporal = parser.getTemporalForEveryPeriod(period);
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);
        return temporalList;
    }

}
