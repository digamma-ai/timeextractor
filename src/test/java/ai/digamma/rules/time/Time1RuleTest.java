package ai.digamma.rules.time;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ai.digamma.entities.TemporalExtraction;
import ai.digamma.rules.general.GeneralTest;

public class Time1RuleTest extends GeneralTest {

    @Test
    public void time1RuleTest1() {

        String toPredict = "7pm";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("7pm", predicted.get(0).getTemporalExpression());
        assertEquals(19, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());

    }

    @Test
    public void time1RuleTest2() {

        String toPredict = "12pm";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("12pm", predicted.get(0).getTemporalExpression());
        assertEquals(12, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());

    }

    @Test
    public void time1RuleTest3() {

        String toPredict = "11 a.m.";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("11 a.m.", predicted.get(0).getTemporalExpression());
        assertEquals(11, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());

    }

    @Test
    public void time1RuleTest4() {

        String toPredict = "11 p.m.";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("11 p.m.", predicted.get(0).getTemporalExpression());
        assertEquals(23, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());

    }

}
