package com.codeminders.labs.timeextractor.rules.set;

import java.util.ArrayList;
import java.util.List;

import com.codeminders.labs.timeextractor.constants.DaysOfRepetition;
import com.codeminders.labs.timeextractor.constants.Frequency;
import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.service.SUTimeService;
import com.codeminders.labs.timeextractor.temporal.entites.Set;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;

// first Tuesday of every month

public class CompositeSet1 extends BaseRule {
    private Temporal temporal;
    private SUTimeService service;

    {
        service = new SUTimeService();
    }

    public CompositeSet1(String firstDayOfWeekOfEveryMonth) {
        System.out.println(firstDayOfWeekOfEveryMonth);
        List<TemporalExtraction> one = service.extractDatesAndTimeFromText(firstDayOfWeekOfEveryMonth, null);
        Temporal dayOfWeek = one.get(0).getTemporal().get(0);
        Set set = new Set();
        set.setFrequency(Frequency.EVERY_MONTH);
        set.setDaysOfRepetition(DaysOfRepetition.DAYS_OF_WEEK);
        dayOfWeek.setSet(set);
        temporal = dayOfWeek;
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
