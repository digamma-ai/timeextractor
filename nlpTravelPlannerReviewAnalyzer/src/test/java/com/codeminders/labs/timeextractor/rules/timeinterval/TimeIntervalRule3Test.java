package com.codeminders.labs.timeextractor.rules.timeinterval;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;

import com.codeminders.labs.timeextractor.entities.TemporalExtraction;
import com.codeminders.labs.timeextractor.rules.general.GeneralTest;

public class TimeIntervalRule3Test extends GeneralTest {

    // TODO: these tests are failing

    @Ignore
    public void timeIntervalRule3Test1() {

        String toPredict = "after 5:30";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict));
        assertEquals("after 5:30", predicted.get(0).getTemporalExpression());
        assertEquals(5, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(30, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getMinutes());

    }

    @Ignore
    public void timeIntervalRule3Test2() {

        String toPredict = "after 5";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict));
        assertEquals("after 5", predicted.get(0).getTemporalExpression());
        assertEquals(5, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());

    }

    @Ignore
    public void timeIntervalRule3Test3() {

        String toPredict = "before 5";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict));
        assertEquals("before 5", predicted.get(0).getTemporalExpression());
        assertEquals(5, predicted.get(0).getTemporal().get(0).getEndDate().getTime().getHours());

    }

    @Ignore
    public void timeIntervalRule3Test4() {

        String toPredict = "before 5.30 CET";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict));
        assertEquals("before 5.30 CET", predicted.get(0).getTemporalExpression());
        assertEquals(5, predicted.get(0).getTemporal().get(0).getEndDate().getTime().getHours());
        assertEquals(30, predicted.get(0).getTemporal().get(0).getEndDate().getTime().getMinutes());

    }

}
