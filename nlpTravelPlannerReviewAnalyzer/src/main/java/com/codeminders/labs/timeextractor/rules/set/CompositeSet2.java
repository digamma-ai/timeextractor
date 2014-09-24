package com.codeminders.labs.timeextractor.rules.set;

import java.util.ArrayList;
import java.util.List;

import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.service.SUTimeService;
import com.codeminders.labs.timeextractor.temporal.entites.DaysOfRepetition;
import com.codeminders.labs.timeextractor.temporal.entites.Frequency;
import com.codeminders.labs.timeextractor.temporal.entites.Set;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;
import com.codeminders.labs.timeextractor.temporal.entites.Type;

public class CompositeSet2 extends BaseRule {
    private Temporal temporal;
    private SUTimeService service;

    {
        service = new SUTimeService();
    }

    public CompositeSet2(String dayOfWeekTime) {
        List<TemporalExtraction> one = service.extractDatesAndTimeFromText(dayOfWeekTime, null);
        Temporal dayOfWeekAndTime = one.get(0).getTemporal().get(0);
        Set set = new Set();
        set.setFrequency(Frequency.WEEKLY);
        set.setDaysOfRepetition(DaysOfRepetition.DAYS_OF_WEEK);
        dayOfWeekAndTime.setSet(set);
        temporal = dayOfWeekAndTime;
        temporal.setType(Type.SET);
        this.confidence = 0.99;

    }

    @Override
    public Type getType() {
        return Type.SET;
    }

    @Override
    public List<Temporal> getTemporal() {
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);
        return temporalList;
    }

}
