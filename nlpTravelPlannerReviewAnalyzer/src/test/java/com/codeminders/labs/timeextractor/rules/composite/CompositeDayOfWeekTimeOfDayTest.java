package com.codeminders.labs.timeextractor.rules.composite;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.codeminders.labs.timeextractor.constants.DayOfWeek;
import com.codeminders.labs.timeextractor.rules.general.GeneralTest;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;

public class CompositeDayOfWeekTimeOfDayTest extends GeneralTest {

    @Test
    public void compositeDayOfWeekTimeOfDayTest1() {

        String toPredict = "Friday morning";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("Friday morning", predicted.get(0).getTemporalExpression());
        assertEquals(DayOfWeek.FRIDAY, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDayOfWeek());
        assertEquals(5, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(DayOfWeek.FRIDAY, predicted.get(0).getTemporal().get(0).getEndDate().getDate().getDayOfWeek());
        assertEquals(12, predicted.get(0).getTemporal().get(0).getEndDate().getTime().getHours());
    }

}
