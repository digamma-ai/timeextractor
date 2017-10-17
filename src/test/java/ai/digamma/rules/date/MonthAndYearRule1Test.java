package ai.digamma.rules.date;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ai.digamma.entities.TemporalExtraction;
import ai.digamma.rules.general.GeneralTest;

public class MonthAndYearRule1Test extends GeneralTest {

    @Test
    public void monthAndYearRule1Test1() {

        String toPredict = "October 2011";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("October 2011", predicted.get(0).getTemporalExpression());
        assertEquals(10, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(2011, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }

    @Test
    public void monthAndYearRule1Test2() {

        String toPredict = "October, 2011";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("October, 2011", predicted.get(0).getTemporalExpression());
        assertEquals(10, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(2011, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }

    @Test
    public void monthAndYearRule1Test3() {

        String toPredict = "October;2011";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("October;2011", predicted.get(0).getTemporalExpression());
        assertEquals(10, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(2011, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }

    @Test
    public void monthAndYearRule1Test4() {

        String toPredict = "October;20111";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("October", predicted.get(0).getTemporalExpression());

    }

}
