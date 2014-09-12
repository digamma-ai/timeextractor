package com.codeminders.labs.timeextractor.rules.date;

import java.util.ArrayList;
import java.util.List;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.utils.TemporalParser;

public class Holidays extends BaseRule {
    private String holidayName;
    private TemporalParser parser;

    public Holidays(String holidayName) {
        parser = new TemporalParser();
        this.holidayName = holidayName;
    }

    @Override
    public Type getType() {
        return Type.DATE;
    }

    @Override
    public List<Temporal> getTemporal() {
        List<Temporal> result = new ArrayList();
        Temporal holiday = parser.getHoliday(holidayName);
        result.add(holiday);
        return result;
    }
}
