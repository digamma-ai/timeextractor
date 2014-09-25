package com.codeminders.labs.timeextractor.rules.composite;

import java.util.ArrayList;
import java.util.List;

import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.service.TemporalExtractionService;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;
import com.codeminders.labs.timeextractor.temporal.entites.Type;

// 934 CET, September, 27, 2014; 934 CET, Sep 27 2014

public class CompositeTime4RuleMonthAndDayRule0 extends BaseRule {
    private TemporalExtractionService service = new TemporalExtractionService();
    private Temporal temporal;
    private double confidence = 0.9;

    public CompositeTime4RuleMonthAndDayRule0(String time, String date) {
        List<TemporalExtraction> one = service.extractDatesAndTimeFromText(time, null);
        List<TemporalExtraction> two = service.extractDatesAndTimeFromText(date, null);
        Temporal temporalTime = one.get(0).getTemporal().get(0);
        Temporal temporalDate = two.get(0).getTemporal().get(0);
        temporalDate.getStartDate().setTime(temporalTime.getStartDate().getTime());
        temporalDate.getEndDate().setTime(temporalTime.getEndDate().getTime());
        this.temporal = temporalDate;

    }

    @Override
    public Type getType() {
        return Type.TIME_DATE;
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
