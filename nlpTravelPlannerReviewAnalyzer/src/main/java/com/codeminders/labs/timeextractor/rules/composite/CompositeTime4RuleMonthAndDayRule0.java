package com.codeminders.labs.timeextractor.rules.composite;

import java.util.ArrayList;
import java.util.List;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.service.SUTimeService;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;

// 934 CET, September, 27, 2014; 934 CET, Sep 27 2014

public class CompositeTime4RuleMonthAndDayRule0 extends BaseRule {
    private SUTimeService service = new SUTimeService();
    private Temporal temporal;

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
        return Type.TIMEDATE;
    }

    @Override
    public List<Temporal> getTemporal() {
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);
        return temporalList;
    }

}
