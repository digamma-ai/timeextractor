package com.codeminders.labs.timeextractor.rules.date;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.codeminders.labs.timeextractor.entities.TemporalExtraction;
import com.codeminders.labs.timeextractor.rules.general.GeneralTest;
import com.codeminders.labs.timeextractor.temporal.entities.DayOfWeek;
import com.codeminders.labs.timeextractor.temporal.entities.WeekOfMonth;

public class DayOfWeekOrderRule1Test extends GeneralTest {

    @Test
    public void DayOfWeekOrderRule1Test1() {

        String toPredict = "the first Tuesday";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict));
        assertEquals("the first Tuesday", predicted.get(0).getTemporalExpression());
        assertEquals(DayOfWeek.TU, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDayOfWeek());
        assertEquals(WeekOfMonth.FIRST, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getWeekOfMonth());

    }

    @Test
    public void dayOfWeekOrderRule1Test2() {

        String toPredict = "the first Tuesday of month";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict));
        assertEquals("the first Tuesday of month", predicted.get(0).getTemporalExpression());
        assertEquals(DayOfWeek.TU, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDayOfWeek());
        assertEquals(WeekOfMonth.FIRST, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getWeekOfMonth());

    }

    @Test
    public void dayOfWeekOrderRule1Test3() {

        String toPredict = "the last Wednesday of the month";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict));
        assertEquals("the last Wednesday of the month", predicted.get(0).getTemporalExpression());
        assertEquals(DayOfWeek.WE, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDayOfWeek());
        assertEquals(WeekOfMonth.LAST, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getWeekOfMonth());

    }

    @Test
    public void dayOfWeekOrderRule1Test4() {

        String toPredict = "second Thursday";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict));
        assertEquals("second Thursday", predicted.get(0).getTemporalExpression());
        assertEquals(DayOfWeek.TH, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDayOfWeek());
        assertEquals(WeekOfMonth.SECOND, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getWeekOfMonth());

    }

    @Test
    public void dayOfWeekOrderRule1Test5() {

        String toPredict = "second Thursdays";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict));
        assertEquals("second Thursdays", predicted.get(0).getTemporalExpression());
        assertEquals(DayOfWeek.TH, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDayOfWeek());
        assertEquals(WeekOfMonth.SECOND, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getWeekOfMonth());

    }

}
