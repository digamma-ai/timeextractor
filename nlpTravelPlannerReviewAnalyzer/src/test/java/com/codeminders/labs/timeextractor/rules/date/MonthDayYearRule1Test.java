package com.codeminders.labs.timeextractor.rules.date;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.codeminders.labs.timeextractor.entities.TemporalExtraction;
import com.codeminders.labs.timeextractor.rules.general.GeneralTest;

public class MonthDayYearRule1Test extends GeneralTest {

    @Test
    public void monthDayYearRule1Test1() {

        String toPredict = " 30.11.2013";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict));
        assertEquals("30.11.2013", predicted.get(0).getTemporalExpression());
        assertEquals(11, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(30, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(2013, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }

    @Test
    public void monthDayYearRule1Test2() {

        String toPredict = " 30.11.10";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict));
        assertEquals("30.11.10", predicted.get(0).getTemporalExpression());
        assertEquals(11, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(30, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(2010, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }

}