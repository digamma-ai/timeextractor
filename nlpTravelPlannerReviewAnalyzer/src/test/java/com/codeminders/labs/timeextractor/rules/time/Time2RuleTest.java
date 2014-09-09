package com.codeminders.labs.timeextractor.rules.time;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.codeminders.labs.timeextractor.rules.general.GeneralTest;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;

public class Time2RuleTest extends GeneralTest {

    @Test
    public void DayOfWeekOrderRule2Test1() {

        String toPredict = "15:01 CET";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("15:01 CET", predicted.get(0).getTemporalExpression());
        assertEquals(15, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(1, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getMinutes());

    }
}
