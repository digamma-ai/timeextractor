package ai.digamma.rules.composite;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import ai.digamma.rules.general.GeneralTest;
import ai.digamma.service.DateTimeExtractor;
import ai.digamma.temporal.entities.Type;
import org.junit.Test;

import ai.digamma.entities.TemporalExtraction;

// composite rule date + time interval: 2014-02-02 + 11am-16pm

public class CompositeDateTimeInterval extends GeneralTest {

    @Test
    public void compositeDateTimeInterval1() {

        String toPredict = "2014-02-02 11am-16pm";
        List<TemporalExtraction> predicted =  new ArrayList<>(DateTimeExtractor.extract(toPredict,settings));
        assertEquals("2014-02-02 11am-16pm", predicted.get(0).getTemporalExpression());
        assertEquals(Type.DATE_TIME_INTERVAL, predicted.get(0).getTemporal().get(0).getType());
        assertEquals(2014, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());
        assertEquals(2, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(2, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(11, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(16, predicted.get(0).getTemporal().get(0).getEndDate().getTime().getHours());

    }

    @Test
    public void compositeDateTimeInterval2() {

        String toPredict = "11am-16pm 2014-02-02";
        List<TemporalExtraction> predicted =  new ArrayList<>(DateTimeExtractor.extract(toPredict,settings));
        assertEquals("11am-16pm 2014-02-02", predicted.get(0).getTemporalExpression());
        assertEquals(Type.DATE_TIME_INTERVAL, predicted.get(0).getTemporal().get(0).getType());
        assertEquals(2014, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());
        assertEquals(2, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(2, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(11, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(16, predicted.get(0).getTemporal().get(0).getEndDate().getTime().getHours());
    }

}
