package ai.digamma.rules.date;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import ai.digamma.entities.TemporalExtraction;
import ai.digamma.rules.general.GeneralTest;
import ai.digamma.temporal.entities.WeekOfMonth;
import ai.digamma.service.DateTimeExtractor;
import org.junit.Test;

import ai.digamma.temporal.entities.DayOfWeek;

public class DayOfWeekOrderRule1Test extends GeneralTest {

    @Test
    public void DayOfWeekOrderRule1Test1() {

        String toPredict = "the first Tuesday";
        List<TemporalExtraction> predicted =  new ArrayList<>(DateTimeExtractor.extract(toPredict,settings));
        assertEquals("the first Tuesday", predicted.get(0).getTemporalExpression());
        assertEquals(DayOfWeek.TU, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDayOfWeek());
        assertEquals(WeekOfMonth.FIRST, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getWeekOfMonth());

    }

    @Test
    public void dayOfWeekOrderRule1Test2() {

        String toPredict = "the first Tuesday of month";
        List<TemporalExtraction> predicted =  new ArrayList<>(DateTimeExtractor.extract(toPredict,settings));
        assertEquals("the first Tuesday of month", predicted.get(0).getTemporalExpression());
        assertEquals(DayOfWeek.TU, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDayOfWeek());
        assertEquals(WeekOfMonth.FIRST, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getWeekOfMonth());

    }

    @Test
    public void dayOfWeekOrderRule1Test3() {

        String toPredict = "the last Wednesday of the month";
        List<TemporalExtraction> predicted =  new ArrayList<>(DateTimeExtractor.extract(toPredict,settings));
        assertEquals("the last Wednesday of the month", predicted.get(0).getTemporalExpression());
        assertEquals(DayOfWeek.WE, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDayOfWeek());
        assertEquals(WeekOfMonth.LAST, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getWeekOfMonth());

    }

    @Test
    public void dayOfWeekOrderRule1Test4() {

        String toPredict = "second Thursday";
        List<TemporalExtraction> predicted =  new ArrayList<>(DateTimeExtractor.extract(toPredict,settings));
        assertEquals("second Thursday", predicted.get(0).getTemporalExpression());
        assertEquals(DayOfWeek.TH, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDayOfWeek());
        assertEquals(WeekOfMonth.SECOND, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getWeekOfMonth());

    }

    @Test
    public void dayOfWeekOrderRule1Test5() {

        String toPredict = "second Thursdays";
        List<TemporalExtraction> predicted =  new ArrayList<>(DateTimeExtractor.extract(toPredict,settings));
        assertEquals("second Thursdays", predicted.get(0).getTemporalExpression());
        assertEquals(DayOfWeek.TH, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDayOfWeek());
        assertEquals(WeekOfMonth.SECOND, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getWeekOfMonth());

    }

}
