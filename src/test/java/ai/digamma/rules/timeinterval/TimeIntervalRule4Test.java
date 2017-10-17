package ai.digamma.rules.timeinterval;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import ai.digamma.rules.general.GeneralTest;
import org.junit.Test;

import ai.digamma.entities.TemporalExtraction;

public class TimeIntervalRule4Test extends GeneralTest {

    @Test
    public void timeIntervalRule4Test() {

        String toPredict = "after 5:30 pm";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("after 5:30 pm", predicted.get(0).getTemporalExpression());
        assertEquals(17, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(30, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getMinutes());

    }

    @Test
    public void timeIntervalRule4Test2() {

        String toPredict = "after 5 pm";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("after 5 pm", predicted.get(0).getTemporalExpression());
        assertEquals(17, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());

    }

    @Test
    public void timeIntervalRule4Test3() {

        String toPredict = "after 5 pm";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("after 5 pm", predicted.get(0).getTemporalExpression());
        assertEquals(17, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(0, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getTimezoneOffset());

    }

}
