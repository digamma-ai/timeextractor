package ai.digamma.rules.date;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import ai.digamma.service.TimeExtractor;
import org.junit.Test;

import ai.digamma.entities.TemporalExtraction;
import ai.digamma.rules.general.GeneralTest;

public class DayOrderAndMonthRule1Test extends GeneralTest {

    @Test
    public void dayOrderAndMonthRule1Test1() {

        String toPredict = "the second of December";
        List<TemporalExtraction> predicted =  new ArrayList<>(TimeExtractor.extract(toPredict,settings));
        assertEquals("the second of December", predicted.get(0).getTemporalExpression());
        assertEquals(2, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(12, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());

    }

    @Test
    public void dayOrderAndMonthRule1Test2() {

        String toPredict = "the second December 2014";
        List<TemporalExtraction> predicted =  new ArrayList<>(TimeExtractor.extract(toPredict,settings));
        assertEquals("the second December 2014", predicted.get(0).getTemporalExpression());
        assertEquals(2, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(12, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(2014, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }

    @Test
    public void dayOrderAndMonthRule1Test3() {

        String toPredict = "sixth December 2014";
        List<TemporalExtraction> predicted =  new ArrayList<>(TimeExtractor.extract(toPredict,settings));
        assertEquals("sixth December 2014", predicted.get(0).getTemporalExpression());
        assertEquals(6, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(12, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(2014, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }

}
