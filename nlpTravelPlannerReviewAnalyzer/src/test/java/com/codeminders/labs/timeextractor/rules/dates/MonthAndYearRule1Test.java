package com.codeminders.labs.timeextractor.rules.dates;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.codeminders.labs.timeextractor.rules.general.GeneralTest;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;

public class MonthAndYearRule1Test extends GeneralTest {

    @Test
    public void monthAndYearRule1Test1() {

        String toPredict = "October 2011";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("October 2011", predicted.get(0).getTemporalExpression());
        assertEquals(10, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(2011, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }

    @Test
    public void monthAndYearRule1Test2() {

        String toPredict = "October, 2011";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("October, 2011", predicted.get(0).getTemporalExpression());
        assertEquals(10, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(2011, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }

    @Test
    public void monthAndYearRule1Test3() {

        String toPredict = "October;2011";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("October;2011", predicted.get(0).getTemporalExpression());
        assertEquals(10, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(2011, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }

    @Test
    public void monthAndYearRule1Test4() {

        String toPredict = "October;20111";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("October", predicted.get(0).getTemporalExpression());

    }

}
