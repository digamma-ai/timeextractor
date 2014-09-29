package com.codeminders.labs.timeextractor.rules.date;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.codeminders.labs.timeextractor.entities.TemporalExtraction;
import com.codeminders.labs.timeextractor.rules.general.GeneralTest;

public class MonthAndDayOrderRule6Test extends GeneralTest {

    @Test
    public void monthAndDayOrderRule6Test1() {

        String toPredict = "March the Fourteenth 2011";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict));
        assertEquals("March the Fourteenth 2011", predicted.get(0).getTemporalExpression());
        assertEquals(3, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(14, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(2011, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }

    @Test
    public void monthAndDayOrderRule6Test2() {

        String toPredict = "March Fourteenth 2011";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict));
        assertEquals("March Fourteenth 2011", predicted.get(0).getTemporalExpression());
        assertEquals(3, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(14, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(2011, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }

    @Test
    public void monthAndDayOrderRule6Test3() {

        String toPredict = "March Fourteenth of 2011";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict));
        assertEquals("March Fourteenth of 2011", predicted.get(0).getTemporalExpression());
        assertEquals(3, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(14, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(2011, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }

    @Test
    public void monthAndDayOrderRule6Test4() {

        String toPredict = "March the Fourteenth, 2011";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict));
        assertEquals("March the Fourteenth, 2011", predicted.get(0).getTemporalExpression());
        assertEquals(3, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(14, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(2011, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }
}
