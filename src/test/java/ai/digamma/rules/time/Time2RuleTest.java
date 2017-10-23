package ai.digamma.rules.time;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import ai.digamma.service.TimeExtractor;
import org.junit.Test;

import ai.digamma.entities.TemporalExtraction;
import ai.digamma.rules.general.GeneralTest;

public class Time2RuleTest extends GeneralTest {

    @Test
    public void DayOfWeekOrderRule2Test1() {

        String toPredict = "15:01 CEST";
        List<TemporalExtraction> predicted =  new ArrayList<>(TimeExtractor.extract(toPredict,settings));
        assertEquals("15:01 CEST", predicted.get(0).getTemporalExpression());
        assertEquals(13, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(1, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getMinutes());

    }
}
