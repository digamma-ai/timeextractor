package com.codemonders.labs.timeextractor.rules.dates;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.codeminders.labs.timeextractor.service.SUTimeService;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;

public class MonthOfYear1Test {
    private SUTimeService service;

    @Before
    public void before() {
        service = new SUTimeService();
    }

    @Test
    public void testMonthOfYear1() {

        String toPredict = "October";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("October", predicted.get(0).getTemporalExpression());
        assertEquals(10, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
    }

    @Test
    public void testMonthOfYear2() {

        String toPredict = "Was October, aaaa";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("October", predicted.get(0).getTemporalExpression());
        assertEquals(10, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(4, predicted.get(0).getFromPosition());
        assertEquals(11, predicted.get(0).getToPosition());

    }

    @Test
    public void testMonthOfYear3() {

        String toPredict = "Was October,";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("October", predicted.get(0).getTemporalExpression());
        assertEquals(10, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(4, predicted.get(0).getFromPosition());
        assertEquals(11, predicted.get(0).getToPosition());

    }

    @Test
    public void testMonthOfYear4() {

        String toPredict = "aOctober,";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals(0, predicted.size());

    }

    @Test
    public void testMonthOfYear5() {

        String toPredict = "Octobera,";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals(0, predicted.size());

    }

    @Test
    public void testMonthOfYear6() {

        String toPredict = "October-";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals(0, predicted.size());
    }

}
