package com.codeminders.labs.timeextractor.rules.date;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.codeminders.labs.timeextractor.entities.TemporalExtraction;
import com.codeminders.labs.timeextractor.rules.general.GeneralTest;

public class MonthAndDayRule3Test extends GeneralTest {

    @Test
    public void monthAndDayRule3Test1() {

        String toPredict = "the 14th of july 2014";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict));
        assertEquals("the 14th of july 2014", predicted.get(0).getTemporalExpression());
        assertEquals(7, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(14, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(2014, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }

    @Test
    public void monthAndDayRule3Test2() {

        String toPredict = "the 14th july 2014";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict));
        assertEquals("the 14th july 2014", predicted.get(0).getTemporalExpression());
        assertEquals(7, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(14, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(2014, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }

    @Test
    public void monthAndDayRule3Test3() {

        String toPredict = "the 14th july";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict));
        assertEquals("the 14th july", predicted.get(0).getTemporalExpression());
        assertEquals(7, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(14, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());

    }

    @Test
    public void monthAndDayRule3Test4() {

        String toPredict = "14th july";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict));
        assertEquals("14th july", predicted.get(0).getTemporalExpression());

        assertEquals(7, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(14, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());

    }

    @Test
    public void monthAndDayRule3Test5() {

        String toPredict = "14th july 2014";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict));
        assertEquals("14th july 2014", predicted.get(0).getTemporalExpression());
        assertEquals(7, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(14, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(2014, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }
}
