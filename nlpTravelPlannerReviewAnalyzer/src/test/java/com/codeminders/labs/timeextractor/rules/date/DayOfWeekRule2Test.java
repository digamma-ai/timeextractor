package com.codeminders.labs.timeextractor.rules.date;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.codeminders.labs.timeextractor.rules.general.GeneralTest;
import com.codeminders.labs.timeextractor.temporal.entites.DayOfWeek;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;

public class DayOfWeekRule2Test extends GeneralTest {

    @Test
    public void DayOfWeekRule2Test1() {

        String toPredict = "Sunday 16";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("Sunday 16", predicted.get(0).getTemporalExpression());
        assertEquals(DayOfWeek.SU, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDayOfWeek());
        assertEquals(16, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());

    }

    @Test
    public void DayOfWeekRule2Test2() {

        String toPredict = "Wed 14";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("Wed 14", predicted.get(0).getTemporalExpression());
        assertEquals(DayOfWeek.WE, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDayOfWeek());
        assertEquals(14, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());

    }

}
