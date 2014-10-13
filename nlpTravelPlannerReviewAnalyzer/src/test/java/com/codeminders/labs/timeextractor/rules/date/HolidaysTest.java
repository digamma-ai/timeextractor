package com.codeminders.labs.timeextractor.rules.date;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.codeminders.labs.timeextractor.entities.TemporalExtraction;
import com.codeminders.labs.timeextractor.rules.general.GeneralTest;

public class HolidaysTest extends GeneralTest {

    @Test
    public void holidaysTest1() {

        String toPredict = "New Year Day";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("New Year Day", predicted.get(0).getTemporalExpression());
        assertEquals(1, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(1, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());

    }

    @Test
    public void holidaysTest5() {

        String toPredict = "Christmas eve";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("Christmas eve", predicted.get(0).getTemporalExpression());
        assertEquals(25, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(12, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());

    }

    @Test
    public void holidaysTest2() {

        String toPredict = "St. Valentine";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("St. Valentine", predicted.get(0).getTemporalExpression());
        assertEquals(14, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(2, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());

    }

    public void holidaysTest3() {

        String toPredict = "halloween";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("halloween", predicted.get(0).getTemporalExpression());
        assertEquals(31, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(10, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());

    }

}
