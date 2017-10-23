package ai.digamma.rules.time;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import ai.digamma.rules.general.GeneralTest;
import ai.digamma.service.TimeExtractor;
import org.junit.Test;

import ai.digamma.entities.TemporalExtraction;

public class Time4RuleTest extends GeneralTest {

    @Test
    public void Time4RuleTest1() {

        String toPredict = "at 5:30";
        List<TemporalExtraction> predicted =  new ArrayList<>(TimeExtractor.extract(toPredict,settings));
        assertEquals("at 5:30", predicted.get(0).getTemporalExpression());
        assertEquals(5, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(30, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getMinutes());
    }

    @Test
    public void Time4RuleTest2() {

        String toPredict = "at 5";
        List<TemporalExtraction> predicted =  new ArrayList<>(TimeExtractor.extract(toPredict,settings));
        assertEquals("at 5", predicted.get(0).getTemporalExpression());
        assertEquals(5, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(00, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getMinutes());
    }

    @Test
    public void Time4RuleTest3() {

        String toPredict = "14h00";
        List<TemporalExtraction> predicted =  new ArrayList<>(TimeExtractor.extract(toPredict,settings));
        assertEquals("14h00", predicted.get(0).getTemporalExpression());
        assertEquals(14, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(00, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getMinutes());
    }

    @Test
    public void Time4RuleTest4() {

        String toPredict = "at 2100 CET";
        List<TemporalExtraction> predicted =  new ArrayList<>(TimeExtractor.extract(toPredict,settings));
        assertEquals("at 2100 CET", predicted.get(0).getTemporalExpression());
        assertEquals(20, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(00, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getMinutes());
    }

    @Test
    public void Time4RuleTest5() {

        String toPredict = "2100 CET";
        List<TemporalExtraction> predicted =  new ArrayList<>(TimeExtractor.extract(toPredict,settings));
        assertEquals("2100 CET", predicted.get(0).getTemporalExpression());
        assertEquals(20, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(00, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getMinutes());
    }

    @Test
    public void Time4RuleTest6() {

        String toPredict = "at 2100";
        List<TemporalExtraction> predicted =  new ArrayList<>(TimeExtractor.extract(toPredict,settings));
        assertEquals("at 2100", predicted.get(0).getTemporalExpression());
        assertEquals(21, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(00, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getMinutes());
    }

    @Test
    public void Time4RuleTest7() {

        String toPredict = "0405 GMT";
        List<TemporalExtraction> predicted =  new ArrayList<>(TimeExtractor.extract(toPredict,settings));
        assertEquals("0405 GMT", predicted.get(0).getTemporalExpression());
        assertEquals(4, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(5, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getMinutes());
    }

    @Test
    public void Time4RuleTest8() {

        String toPredict = "1205 HKT";
        List<TemporalExtraction> predicted =  new ArrayList<>(TimeExtractor.extract(toPredict,settings));
        assertEquals("1205 HKT", predicted.get(0).getTemporalExpression());
        assertEquals(4, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(05, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getMinutes());
    }
}
