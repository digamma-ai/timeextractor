package ai.digamma.rules.date;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import ai.digamma.entities.TemporalExtraction;
import ai.digamma.service.DateTimeExtractor;
import ai.digamma.temporal.entities.WeekOfMonth;
import org.junit.Test;

import ai.digamma.rules.general.GeneralTest;
import ai.digamma.temporal.entities.DayOfWeek;

public class DayOfWeekOrderRule2Test extends GeneralTest {

    @Test
    public void DayOfWeekOrderRule2Test1() {

        String toPredict = "1st Tuesday of the month";
        List<TemporalExtraction> predicted =  new ArrayList<>(DateTimeExtractor.extract(toPredict,settings));
        assertEquals("1st Tuesday of the month", predicted.get(0).getTemporalExpression());
        assertEquals(DayOfWeek.TU, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDayOfWeek());
        assertEquals(WeekOfMonth.FIRST, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getWeekOfMonth());

    }

    @Test
    public void DayOfWeekOrderRule2Test2() {

        String toPredict = "1st Tuesday";
        List<TemporalExtraction> predicted =  new ArrayList<>(DateTimeExtractor.extract(toPredict,settings));
        assertEquals("1st Tuesday", predicted.get(0).getTemporalExpression());
        assertEquals(DayOfWeek.TU, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDayOfWeek());
        assertEquals(WeekOfMonth.FIRST, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getWeekOfMonth());

    }

    @Test
    public void DayOfWeekOrderRule2Test3() {

        String toPredict = "3rd Wednesday";
        List<TemporalExtraction> predicted =  new ArrayList<>(DateTimeExtractor.extract(toPredict,settings));
        assertEquals("3rd Wednesday", predicted.get(0).getTemporalExpression());
        assertEquals(DayOfWeek.WE, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDayOfWeek());
        assertEquals(WeekOfMonth.THIRD, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getWeekOfMonth());

    }
}
