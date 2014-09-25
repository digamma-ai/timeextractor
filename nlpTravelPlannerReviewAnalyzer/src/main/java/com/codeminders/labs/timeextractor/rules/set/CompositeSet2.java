package com.codeminders.labs.timeextractor.rules.set;

import java.util.ArrayList;
import java.util.List;

import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.service.TemporalExtractionService;
import com.codeminders.labs.timeextractor.temporal.entites.DaysOfRepetition;
import com.codeminders.labs.timeextractor.temporal.entites.Frequency;
import com.codeminders.labs.timeextractor.temporal.entites.Set;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;
import com.codeminders.labs.timeextractor.temporal.entites.Type;

public class CompositeSet2 extends BaseRule {
    private Temporal temporal;
    private TemporalExtractionService service;
    private double confidence = 0.9;

    {
        service = new TemporalExtractionService();
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

    @Override
    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

}
