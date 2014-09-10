package com.codeminders.labs.timeextraction.rules.dateinterval;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.codeminders.labs.timeextractor.rules.general.GeneralTest;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;

public class MonthToMonthRule1Test extends GeneralTest {

    @Test
    public void monthToMonthRuleTest1() {

        String toPredict = "April through October 2014";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("April through October 2014", predicted.get(0).getTemporalExpression());
        assertEquals(4, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(10, predicted.get(0).getTemporal().get(0).getEndDate().getDate().getMonth());
        assertEquals(2014, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());
        assertEquals(2014, predicted.get(0).getTemporal().get(0).getEndDate().getDate().getYear());
    }
}
