package ai.digamma.rules.composite;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import ai.digamma.service.TimeExtractor;
import org.junit.Test;

import ai.digamma.entities.TemporalExtraction;
import ai.digamma.rules.general.GeneralTest;

public class CompositeDayOfWeekTimeOfDayTest extends GeneralTest {

    @Test
    public void compositeDayOfWeekTimeOfDayTest1() {

        String toPredict = "Friday morning";
        List<TemporalExtraction> predicted =  new ArrayList<>(TimeExtractor.extract(toPredict,settings));
        assertEquals("Friday morning", predicted.get(0).getTemporalExpression());
        System.out.println(predicted.get(0).getTemporalExpression());
    }

}
