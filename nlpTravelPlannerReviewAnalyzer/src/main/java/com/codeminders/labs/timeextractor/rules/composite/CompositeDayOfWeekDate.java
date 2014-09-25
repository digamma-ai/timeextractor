package com.codeminders.labs.timeextractor.rules.composite;

import java.util.ArrayList;
import java.util.List;

import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.service.TemporalExtractionService;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;
import com.codeminders.labs.timeextractor.temporal.entites.Type;

public class CompositeDayOfWeekDate extends BaseRule {
    private TemporalExtractionService service;
    private Temporal temporal;
    private double confidence = 0.9;

    {
        service = new TemporalExtractionService();
    }

    public CompositeDayOfWeekDate(String dayOfWeek, String date) {
        List<TemporalExtraction> one = service.extractDatesAndTimeFromText(dayOfWeek, null);
        List<TemporalExtraction> two = service.extractDatesAndTimeFromText(date, null);

        Temporal dayOfWeekTemporal = one.get(0).getTemporal().get(0);
        Temporal dateTemporal = two.get(0).getTemporal().get(0);

        dateTemporal.getStartDate().getDate().setDayOfWeek(dayOfWeekTemporal.getStartDate().getDate().getDayOfWeek());
        dateTemporal.getEndDate().getDate().setDayOfWeek(dayOfWeekTemporal.getEndDate().getDate().getDayOfWeek());
        dateTemporal.setType(Type.SET);
        this.temporal = dateTemporal;

    }

    public CompositeDayOfWeekDate(ArrayList<String> date1, ArrayList<String> date2) {
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

        this.temporal = new Temporal(dateStartTemporal.getStartDate(), dateEndTemporal.getEndDate());

    }

    @Override
    public Type getType() {
        return Type.DATE;
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