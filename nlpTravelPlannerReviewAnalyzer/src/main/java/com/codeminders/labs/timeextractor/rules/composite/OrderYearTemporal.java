package com.codeminders.labs.timeextractor.rules.composite;

import java.util.ArrayList;
import java.util.List;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.service.SUTimeService;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;

public class OrderYearTemporal extends BaseRule {

    private SUTimeService service = new SUTimeService();
    private Temporal temporal;

    @Override
    public Type getType() {
        return Type.DATE;
    }

    public OrderYearTemporal(String dayOfWeek, String year) {
        List<TemporalExtraction> one = service.extractDatesAndTimeFromText(dayOfWeek, null);
        List<TemporalExtraction> two = service.extractDatesAndTimeFromText(year, null);
        Temporal temporalDayOfWeek = one.get(0).getTemporal().get(0);
        Temporal temporalYear = two.get(0).getTemporal().get(0);
        temporalDayOfWeek.getStartDate().getDate().setYear(temporalYear.getStartDate().getDate().getYear());
        temporalDayOfWeek.getEndDate().getDate().setYear(temporalYear.getEndDate().getDate().getYear());
        this.temporal = temporalDayOfWeek;
    }

    @Override
    public List<Temporal> getTemporal() {
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);
        return temporalList;
    }

}
