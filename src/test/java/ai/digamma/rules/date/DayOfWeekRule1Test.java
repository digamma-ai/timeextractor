package ai.digamma.rules.date;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import ai.digamma.service.DateTimeExtractor;
import org.junit.Test;

import ai.digamma.entities.TemporalExtraction;
import ai.digamma.rules.general.GeneralTest;
import ai.digamma.temporal.entities.DayOfWeek;

public class DayOfWeekRule1Test extends GeneralTest {

    @Test
    public void dayOfWeekRule1Test1() {

        String toPredict = "Sunday";
        List<TemporalExtraction> predicted =  new ArrayList<>(DateTimeExtractor.extract(toPredict,settings));
        assertEquals("Sunday", predicted.get(0).getTemporalExpression());
        assertEquals(DayOfWeek.SU, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDayOfWeek());

    }

    @Test
    public void dayOfWeekRule1Test2() {

        String toPredict = "Wed";
        List<TemporalExtraction> predicted =  new ArrayList<>(DateTimeExtractor.extract(toPredict,settings));
        assertEquals("Wed", predicted.get(0).getTemporalExpression());
        assertEquals(DayOfWeek.WE, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDayOfWeek());

    }

}
