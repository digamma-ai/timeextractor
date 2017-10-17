package ai.digamma.rules.date;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import ai.digamma.rules.general.GeneralTest;
import org.junit.Test;

import ai.digamma.entities.TemporalExtraction;

public class MonthOfYear1Test extends GeneralTest {

    @Test
    public void testMonthOfYear1() {

        String toPredict = "October";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("October", predicted.get(0).getTemporalExpression());
        assertEquals(10, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
    }

    @Test
    public void testMonthOfYear2() {

        String toPredict = "Was October, aaaa";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("October", predicted.get(0).getTemporalExpression());
        assertEquals(10, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(4, predicted.get(0).getFromPosition());
        assertEquals(11, predicted.get(0).getToPosition());

    }

    @Test
    public void testMonthOfYear4() {

        String toPredict = "aOctober,";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals(0, predicted.size());

    }

    @Test
    public void testMonthOfYear5() {

        String toPredict = "Octobera,";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals(0, predicted.size());

    }

    @Test
    public void testMonthOfYear6() {

        String toPredict = "October-";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("October", predicted.get(0).getTemporalExpression());
        assertEquals(10, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
    }

}
