package com.codeminders.labs.timeextractor.rules.composite;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.codeminders.labs.timeextractor.entities.TemporalExtraction;
import com.codeminders.labs.timeextractor.rules.general.GeneralTest;
import com.codeminders.labs.timeextractor.temporal.entities.DayOfWeek;

public class CompositeDayOfWeekTimeOfDayTest extends GeneralTest {

    @Test
    public void compositeDayOfWeekTimeOfDayTest1() {

        String toPredict = "Friday morning";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("Friday morning", predicted.get(0).getTemporalExpression());
        System.out.println(predicted.get(0).getTemporalExpression());
    }

}
