package com.codeminders.labs.timeextractor.rules.date;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.codeminders.labs.timeextractor.rules.general.GeneralTest;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;

public class DayOrderAndMonthRule1Test extends GeneralTest {

    @Test
    public void dayOrderAndMonthRule1Test1() {

        String toPredict = "the second of December";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("the second of December", predicted.get(0).getTemporalExpression());
        assertEquals(2, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(12, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());

    }

    @Test
    public void dayOrderAndMonthRule1Test2() {

        String toPredict = "the second December 2014";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("the second December 2014", predicted.get(0).getTemporalExpression());
        assertEquals(2, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(12, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(2014, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }

    @Test
    public void dayOrderAndMonthRule1Test3() {

        String toPredict = "sixth December 2014";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("sixth December 2014", predicted.get(0).getTemporalExpression());
        assertEquals(6, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(12, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(2014, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }

}
