package com.codeminders.labs.timeextractor.rules.date;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.codeminders.labs.timeextractor.rules.general.GeneralTest;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;

public class MonthAndDayRule1Test extends GeneralTest {

    @Test
    public void monthAndDayRule0Test1() {

        String toPredict = "July 14th 2014";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("July 14th 2014", predicted.get(0).getTemporalExpression());
        assertEquals(7, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(14, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(2014, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }

    @Test
    public void monthAndDayRule0Test2() {

        String toPredict = "July the 14th 2014";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("July the 14th 2014", predicted.get(0).getTemporalExpression());
        assertEquals(7, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(14, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(2014, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }

    @Test
    public void monthAndDayRule0Test3() {

        String toPredict = "July the 14th of 2014";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("July the 14th of 2014", predicted.get(0).getTemporalExpression());
        assertEquals(7, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(14, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(2014, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }

    @Test
    public void monthAndDayRule0Test4() {

        String toPredict = "July the 14th";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("July the 14th", predicted.get(0).getTemporalExpression());
        assertEquals(7, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(14, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());

    }

    @Test
    public void monthAndDayRule0Test5() {

        String toPredict = "July 14th";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("July 14th", predicted.get(0).getTemporalExpression());
        assertEquals(7, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(14, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());

    }
}