package com.codeminders.labs.timeextractor.rules.date;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.utils.TemporalParser;

public class TodayTomorrowEtc extends BaseRule {
    private String date;
    private TemporalParser parser;

    public TodayTomorrowEtc(String date) {
        this.date = date;
        parser = new TemporalParser();
    }

    @Override
    public Type getType() {
        return Type.DATE;
    }

    @Override
    public List<Temporal> getTemporal() {
        LocalDate currentDate = new LocalDate();
        Temporal temporal = parser.getRelativeTemporalObjectByProperty(date, currentDate);
        List<Temporal> result = new ArrayList<Temporal>();
        result.add(temporal);
        return result;
    }
}
