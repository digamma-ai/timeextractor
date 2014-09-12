package com.codeminders.labs.timeextractor.rules.date;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.codeminders.labs.timeextractor.rules.general.GeneralTest;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;

public class HolidaysTest extends GeneralTest {

    @Test
    public void holidaysTest1() {

        String toPredict = "New Year Day";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("New Year Day", predicted.get(0).getTemporalExpression());
        assertEquals(1, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(1, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());

    }

    @Test
    public void holidaysTest5() {

        String toPredict = "Christmas eve";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("Christmas eve", predicted.get(0).getTemporalExpression());
        assertEquals(25, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(12, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());

    }

    @Test
    public void holidaysTest2() {

        String toPredict = "St. Valentine";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("St. Valentine", predicted.get(0).getTemporalExpression());
        assertEquals(14, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(2, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());

    }

    @Test
    public void holidaysTest3() {

        String toPredict = "halloween";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("halloween", predicted.get(0).getTemporalExpression());
        assertEquals(31, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(10, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());

    }

    @Test
    public void holidaysTest4() {

        String toPredict = "independence day";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("independence day", predicted.get(0).getTemporalExpression());
        assertEquals(4, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(7, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());

    }
}
