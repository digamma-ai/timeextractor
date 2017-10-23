package ai.digamma.rules.date;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import ai.digamma.entities.TemporalExtraction;
import ai.digamma.rules.general.GeneralTest;
import ai.digamma.service.TimeExtractor;
import ai.digamma.temporal.entities.DayOfWeek;
import org.junit.Test;

public class DayOfWeekRule3Test extends GeneralTest {

    @Test
    public void DayOfWeekRule3Test1() {

        String toPredict = "Sunday 16 2014";
        List<TemporalExtraction> predicted =  new ArrayList<>(TimeExtractor.extract(toPredict,settings));
        assertEquals("Sunday 16 2014", predicted.get(0).getTemporalExpression());
        assertEquals(DayOfWeek.SU, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDayOfWeek());
        assertEquals(16, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(2014, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }

    @Test
    public void DayOfWeekRule3Test2() {

        String toPredict = "Sunday 16, 2014";
        List<TemporalExtraction> predicted =  new ArrayList<>(TimeExtractor.extract(toPredict,settings));
        assertEquals("Sunday 16, 2014", predicted.get(0).getTemporalExpression());
        assertEquals(DayOfWeek.SU, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDayOfWeek());
        assertEquals(16, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(2014, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }

    @Test
    public void DayOfWeekRule3Test3() {

        String toPredict = "Sunday, 16, 2014";
        List<TemporalExtraction> predicted =  new ArrayList<>(TimeExtractor.extract(toPredict,settings));
        assertEquals("Sunday, 16, 2014", predicted.get(0).getTemporalExpression());
        assertEquals(DayOfWeek.SU, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDayOfWeek());
        assertEquals(16, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(2014, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }

    @Test
    public void DayOfWeekRule3Test4() {

        String toPredict = "Sun 16, 2014";
        List<TemporalExtraction> predicted =  new ArrayList<>(TimeExtractor.extract(toPredict,settings));
        assertEquals("Sun 16, 2014", predicted.get(0).getTemporalExpression());
        assertEquals(DayOfWeek.SU, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDayOfWeek());
        assertEquals(16, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(2014, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());

    }

}
