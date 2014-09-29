package com.codeminders.labs.timeextractor.rules.date;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.codeminders.labs.timeextractor.entities.TemporalExtraction;
import com.codeminders.labs.timeextractor.rules.general.GeneralTest;
import com.codeminders.labs.timeextractor.temporal.entities.DayOfWeek;

public class MonthAndDayRule5Test extends GeneralTest {

    @Test
    public void monthAndDayRule4Test1() {

        String toPredict = "Sunday 17 of July";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict));
        assertEquals("Sunday 17 of July", predicted.get(0).getTemporalExpression());
        assertEquals(7, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(DayOfWeek.SU, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDayOfWeek());
        assertEquals(17, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());

    }

}
