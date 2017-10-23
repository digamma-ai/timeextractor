package ai.digamma.rules.duration;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import ai.digamma.service.DateTimeExtractor;
import org.junit.Test;

import ai.digamma.entities.TemporalExtraction;
import ai.digamma.rules.general.GeneralTest;
import ai.digamma.temporal.entities.Type;

public class DurationRule2Test extends GeneralTest {

    @Test
    public void durationRule2Test1() {

        String toPredict = "three minutes";
        List<TemporalExtraction> predicted =  new ArrayList<>(DateTimeExtractor.extract(toPredict,settings));
        assertEquals("three minutes", predicted.get(0).getTemporalExpression());
        assertEquals(Type.DURATION, predicted.get(0).getTemporal().get(0).getType());
        assertEquals(3, predicted.get(0).getTemporal().get(0).getDuration().getMinutes());

    }

}
