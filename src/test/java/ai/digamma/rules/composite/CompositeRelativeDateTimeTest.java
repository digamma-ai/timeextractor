package ai.digamma.rules.composite;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import ai.digamma.rules.general.GeneralTest;
import ai.digamma.service.TimeExtractor;
import org.junit.Test;

import ai.digamma.entities.TemporalExtraction;

public class CompositeRelativeDateTimeTest extends GeneralTest {

    @Test
    public void compositeRelativeDateTime() {

        String toPredict = "tomorrow 11:30am";
        List<TemporalExtraction> predicted =  new ArrayList<>(TimeExtractor.extract(toPredict,settings));
        assertEquals("tomorrow 11:30am", predicted.get(0).getTemporalExpression());
        assertEquals(11, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(30, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getMinutes());
    }

    @Test
    public void compositeRelativeDateTime2() {

        String toPredict = "yesterday 11:30am";
        List<TemporalExtraction> predicted =  new ArrayList<>(TimeExtractor.extract(toPredict,settings));
        assertEquals("yesterday 11:30am", predicted.get(0).getTemporalExpression());
        assertEquals(11, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(30, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getMinutes());
    }

    @Test
    public void compositeRelativeDateTime3() {

        String toPredict = "yesterday until 11:30am";
        List<TemporalExtraction> predicted =  new ArrayList<>(TimeExtractor.extract(toPredict,settings));
        assertEquals("yesterday until 11:30am", predicted.get(0).getTemporalExpression());
        assertEquals(11, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(30, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getMinutes());
    }

}
