package com.codeminders.labs.timeextractor.rules.timeinterval;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.codeminders.labs.timeextractor.rules.general.GeneralTest;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;

public class TimeIntervalRule2Test extends GeneralTest {

    @Test
    public void timeIntervalRule2Test() {

        String toPredict = "between morning and evening";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("between morning and evening", predicted.get(0).getTemporalExpression());
        assertEquals(5, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(22, predicted.get(0).getTemporal().get(0).getEndDate().getTime().getHours());

    }

    @Test
    public void timeIntervalRule2Test2() {

        String toPredict = "between morning and midday";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("between morning and midday", predicted.get(0).getTemporalExpression());
        assertEquals(5, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(12, predicted.get(0).getTemporal().get(0).getEndDate().getTime().getHours());

    }

}
