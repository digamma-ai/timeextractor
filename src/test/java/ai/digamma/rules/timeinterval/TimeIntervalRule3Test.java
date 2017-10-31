package ai.digamma.rules.timeinterval;

import ai.digamma.entities.TemporalExtraction;
import ai.digamma.service.DateTimeExtractor;
import static org.junit.Assert.assertEquals;

import ai.digamma.rules.general.GeneralTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TimeIntervalRule3Test extends GeneralTest {

    @Test
    public void timeIntervalRule3Test1() {

        String toPredict = "after 5:30";
        List<TemporalExtraction> predicted =  new ArrayList<>(DateTimeExtractor.extract(toPredict,settings));
        assertEquals("after 5:30", predicted.get(0).getTemporalExpression());
        assertEquals(5, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(30, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getMinutes());

    }

    @Test
    public void timeIntervalRule3Test2() {

        String toPredict = "after 5";
        List<TemporalExtraction> predicted =  new ArrayList<>(DateTimeExtractor.extract(toPredict,settings));
        assertEquals("after 5", predicted.get(0).getTemporalExpression());
        assertEquals(5, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());

    }

    @Test
    public void timeIntervalRule3Test3() {

        String toPredict = "before 5";
        List<TemporalExtraction> predicted =  new ArrayList<>(DateTimeExtractor.extract(toPredict,settings));
        assertEquals("before 5", predicted.get(0).getTemporalExpression());
        assertEquals(5, predicted.get(0).getTemporal().get(0).getEndDate().getTime().getHours());

    }

    @Test
    public void timeIntervalRule3Test4() {

        String toPredict = "before 5.30 CET";
        List<TemporalExtraction> predicted =  new ArrayList<>(DateTimeExtractor.extract(toPredict,settings));
        assertEquals("before 5.30 CET", predicted.get(0).getTemporalExpression());
        assertEquals(4, predicted.get(0).getTemporal().get(0).getEndDate().getTime().getHours());
        assertEquals(30, predicted.get(0).getTemporal().get(0).getEndDate().getTime().getMinutes());

    }

}
