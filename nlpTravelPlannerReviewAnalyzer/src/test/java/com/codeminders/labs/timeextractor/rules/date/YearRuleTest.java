package com.codeminders.labs.timeextractor.rules.date;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.codeminders.labs.timeextractor.entities.TemporalExtraction;
import com.codeminders.labs.timeextractor.rules.general.GeneralTest;

public class YearRuleTest extends GeneralTest {

    @Test
    public void testYear1() {

        String toPredict = "2014";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("2014", predicted.get(0).getTemporalExpression());
        assertEquals(2014, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }

    @Test
    public void testYear3() {

        String toPredict = "2033";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("2033", predicted.get(0).getTemporalExpression());
        assertEquals(2033, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }


    @Test
    public void testYear4() {

        String toPredict = "2000n";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals(0, predicted.size());
    }

    @Test
    public void testYear5() {

        String toPredict = "2000,";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("2000", predicted.get(0).getTemporalExpression());
    }

    @Test
    public void testYear6() {
        String toPredict = "2000.";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("2000", predicted.get(0).getTemporalExpression());
        assertEquals(2000, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }

}
