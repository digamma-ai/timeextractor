package com.codeminders.labs.timeextractor.rules.composite;

import java.util.ArrayList;
import java.util.List;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.service.SUTimeService;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;

// Friday night, Tuesday morning

public class CompositeDayOfWeekTimeOfDay extends BaseRule {
    private SUTimeService service;
    private Temporal temporal;

    {
        service = new SUTimeService();
    }

    public CompositeDayOfWeekTimeOfDay(String dayOfWeek, String timeOfDay) {
        List<TemporalExtraction> one = service.extractDatesAndTimeFromText(dayOfWeek, null);
        List<TemporalExtraction> two = service.extractDatesAndTimeFromText(timeOfDay, null);

        Temporal dayOfWeekTemporal = one.get(0).getTemporal().get(0);
        Temporal timeOfDayTemporal = two.get(0).getTemporal().get(0);

        timeOfDayTemporal.getStartDate().setDate(dayOfWeekTemporal.getStartDate().getDate());
        timeOfDayTemporal.getEndDate().setDate(dayOfWeekTemporal.getEndDate().getDate());

        this.temporal = timeOfDayTemporal;

    }

    @Override
    public Type getType() {
        return Type.DATE_TIME_INTERVAL;
    }

    @Override
    public List<Temporal> getTemporal() {
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);
        return temporalList;
    }

}
