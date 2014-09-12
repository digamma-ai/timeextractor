package com.codeminders.labs.timeextractor.rules.date;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.codeminders.labs.timeextractor.constants.DayOfWeek;
import com.codeminders.labs.timeextractor.constants.WeekOfMonth;
import com.codeminders.labs.timeextractor.rules.general.GeneralTest;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;

public class DayOfWeekOrderRule2Test extends GeneralTest {

    @Test
    public void DayOfWeekOrderRule2Test1() {

        String toPredict = "1st Tuesday of the month";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("1st Tuesday of the month", predicted.get(0).getTemporalExpression());
        assertEquals(DayOfWeek.TUESDAY, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDayOfWeek());
        assertEquals(WeekOfMonth.FIRST, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getWeekOfMonth());

    }

    @Test
    public void DayOfWeekOrderRule2Test2() {

        String toPredict = "1st Tuesday";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("1st Tuesday", predicted.get(0).getTemporalExpression());
        assertEquals(DayOfWeek.TUESDAY, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDayOfWeek());
        assertEquals(WeekOfMonth.FIRST, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getWeekOfMonth());

    }

    @Test
    public void DayOfWeekOrderRule2Test3() {

        String toPredict = "3rd Wednesday";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("3rd Wednesday", predicted.get(0).getTemporalExpression());
        assertEquals(DayOfWeek.WEDNESDAY, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDayOfWeek());
        assertEquals(WeekOfMonth.THIRD, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getWeekOfMonth());

    }
}
