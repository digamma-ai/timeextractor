package ai.digamma.rules.duration;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ai.digamma.entities.TemporalExtraction;
import ai.digamma.rules.general.GeneralTest;
import ai.digamma.temporal.entities.Type;

public class DurationRule1Test extends GeneralTest {

    @Test
    public void durationRule1Test() {

        String toPredict = "3 minutes";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("3 minutes", predicted.get(0).getTemporalExpression());
        assertEquals(Type.DURATION, predicted.get(0).getTemporal().get(0).getType());
        assertEquals(3, predicted.get(0).getTemporal().get(0).getDuration().getMinutes());

    }

    @Test
    public void durationRule2Test() {

        String toPredict = "2 days";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("2 days", predicted.get(0).getTemporalExpression());
        assertEquals(Type.DURATION, predicted.get(0).getTemporal().get(0).getType());
        assertEquals(2, predicted.get(0).getTemporal().get(0).getDuration().getDays());

    }

    @Test
    public void durationRule3Test() {

        String toPredict = "3 year";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("3 year", predicted.get(0).getTemporalExpression());
        assertEquals(Type.DURATION, predicted.get(0).getTemporal().get(0).getType());
        assertEquals(3, predicted.get(0).getTemporal().get(0).getDuration().getYears());

    }
}
