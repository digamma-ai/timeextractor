package com.codeminders.labs.timeextractor.rules.time;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.codeminders.labs.timeextractor.entities.TemporalExtraction;
import com.codeminders.labs.timeextractor.rules.general.GeneralTest;

public class Time3RuleTest extends GeneralTest {

    @Test
    public void Time3RuleTest1() {

        String toPredict = "at 5.33 am ";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("at 5.33 am", predicted.get(0).getTemporalExpression());
        assertEquals(5, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(33, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getMinutes());
    }

    @Test
    public void Time3RuleTest2() {

        String toPredict = "at 5pm";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("at 5pm", predicted.get(0).getTemporalExpression());
        assertEquals(17, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(00, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getMinutes());
    }

    @Test
    public void Time3RuleTest3() {

        String toPredict = "at 5pm";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("at 5pm", predicted.get(0).getTemporalExpression());
        assertEquals(17, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(00, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getMinutes());

    }

}
