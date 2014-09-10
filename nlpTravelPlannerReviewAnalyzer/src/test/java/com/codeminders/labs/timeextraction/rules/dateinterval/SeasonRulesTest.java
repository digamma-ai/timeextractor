package com.codeminders.labs.timeextraction.rules.dateinterval;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.codeminders.labs.timeextractor.rules.general.GeneralTest;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;

public class SeasonRulesTest extends GeneralTest {

    @Test
    public void seasonRulesTest1() {

        String toPredict = "spring";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("spring", predicted.get(0).getTemporalExpression());
        assertEquals(3, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(5, predicted.get(0).getTemporal().get(0).getEndDate().getDate().getMonth());

    }

    @Test
    public void seasonRulesTest2() {

        String toPredict = "winter";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("winter", predicted.get(0).getTemporalExpression());
        assertEquals(12, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(2, predicted.get(0).getTemporal().get(0).getEndDate().getDate().getMonth());

    }

    @Test
    public void seasonRulesTest3() {

        String toPredict = "summer";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("summer", predicted.get(0).getTemporalExpression());
        assertEquals(6, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(8, predicted.get(0).getTemporal().get(0).getEndDate().getDate().getMonth());

    }

    @Test
    public void seasonRulesTest4() {

        String toPredict = "autumn";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("autumn", predicted.get(0).getTemporalExpression());
        assertEquals(9, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(11, predicted.get(0).getTemporal().get(0).getEndDate().getDate().getMonth());

    }

}
