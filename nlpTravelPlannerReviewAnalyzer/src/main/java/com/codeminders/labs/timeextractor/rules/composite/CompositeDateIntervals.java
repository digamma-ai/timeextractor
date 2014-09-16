package com.codeminders.labs.timeextractor.rules.composite;

import java.util.ArrayList;
import java.util.List;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.service.SUTimeService;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;

public class CompositeDateIntervals extends BaseRule {
    private SUTimeService service;
    private Temporal temporal;

    {
        service = new SUTimeService();
    }

    public CompositeDateIntervals(ArrayList<String> date1, ArrayList<String> date2) {
        String dateStart = "";
        String datEnd = "";

        for (String dateParts : date1) {
            dateStart = dateStart + dateParts + " ";
        }
        for (String dateParts : date2) {
            datEnd = datEnd + dateParts + " ";
        }

        List<TemporalExtraction> one = service.extractDatesAndTimeFromText(dateStart, null);
        List<TemporalExtraction> two = service.extractDatesAndTimeFromText(datEnd, null);

        Temporal dateStartTemporal = one.get(0).getTemporal().get(0);
        Temporal dateEndTemporal = two.get(0).getTemporal().get(0);

        if (dateStartTemporal.getStartDate().getDate().getYear() == 0 && dateEndTemporal.getStartDate().getDate().getYear() != 0) {
            dateStartTemporal.getStartDate().getDate().setYear(dateEndTemporal.getStartDate().getDate().getYear());
            dateStartTemporal.getEndDate().getDate().setYear(dateEndTemporal.getStartDate().getDate().getYear());

        }
        this.temporal = new Temporal(dateStartTemporal.getStartDate(), dateEndTemporal.getEndDate());

    }

    @Override
    public Type getType() {
        return Type.DATE_INTERVAL;
    }

    @Override
    public List<Temporal> getTemporal() {
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);
        return temporalList;
    }

}
