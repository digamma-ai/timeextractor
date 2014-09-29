package com.codeminders.labs.timeextractor.rules.timeinterval;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.codeminders.labs.timeextractor.entities.TemporalExtraction;
import com.codeminders.labs.timeextractor.rules.general.GeneralTest;

public class TimeOfDayRuleTest extends GeneralTest {

    @Test
    public void timeOfDayRuleTest1() {

        String toPredict = "morning";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict));
        assertEquals("morning", predicted.get(0).getTemporalExpression());
        assertEquals(5, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(12, predicted.get(0).getTemporal().get(0).getEndDate().getTime().getHours());

    }

    @Test
    public void timeOfDayRuleTest2() {

        String toPredict = "afternoon";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict));
        assertEquals("afternoon", predicted.get(0).getTemporalExpression());
        assertEquals(12, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(18, predicted.get(0).getTemporal().get(0).getEndDate().getTime().getHours());

    }

    @Test
    public void timeOfDayRuleTest3() {

        String toPredict = "evening";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict));
        assertEquals("evening", predicted.get(0).getTemporalExpression());
        assertEquals(18, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(22, predicted.get(0).getTemporal().get(0).getEndDate().getTime().getHours());

    }

    @Test
    public void timeOfDayRuleTest4() {

        String toPredict = "night";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict));
        assertEquals("night", predicted.get(0).getTemporalExpression());
        assertEquals(22, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(23, predicted.get(0).getTemporal().get(0).getEndDate().getTime().getHours());

    }

    @Test
    public void timeOfDayRuleTest5() {

        String toPredict = "midnight";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict));
        assertEquals("midnight", predicted.get(0).getTemporalExpression());
        assertEquals(0, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(0, predicted.get(0).getTemporal().get(0).getEndDate().getTime().getHours());

    }

    @Test
    public void timeOfDayRuleTest6() {

        String toPredict = "noon";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict));
        assertEquals("noon", predicted.get(0).getTemporalExpression());
        assertEquals(12, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(12, predicted.get(0).getTemporal().get(0).getEndDate().getTime().getHours());

    }

    @Test
    public void timeOfDayRuleTest7() {

        String toPredict = "midday";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict));
        assertEquals("midday", predicted.get(0).getTemporalExpression());
        assertEquals(12, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(12, predicted.get(0).getTemporal().get(0).getEndDate().getTime().getHours());

    }

}
