package com.codeminders.labs.timeextractor.rules.time;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.codeminders.labs.timeextractor.entities.TemporalExtraction;
import com.codeminders.labs.timeextractor.rules.general.GeneralTest;

public class Time2RuleTest extends GeneralTest {

    @Test
    public void DayOfWeekOrderRule2Test1() {

        String toPredict = "15:01 CEST";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("15:01 CEST", predicted.get(0).getTemporalExpression());
        assertEquals(13, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(1, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getMinutes());

    }
}
