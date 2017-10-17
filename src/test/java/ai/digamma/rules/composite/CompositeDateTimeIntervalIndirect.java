package ai.digamma.rules.composite;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import ai.digamma.rules.general.GeneralTest;
import ai.digamma.temporal.entities.Type;
import org.junit.Test;

import ai.digamma.entities.TemporalExtraction;

public class CompositeDateTimeIntervalIndirect extends GeneralTest {

    @Test
    public void compositeDateTimeInterval1() {

        String toPredict = "2014-02-02 evening";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("2014-02-02 evening", predicted.get(0).getTemporalExpression());
        assertEquals(Type.DATE_TIME_INTERVAL_INDIRECT, predicted.get(0).getTemporal().get(0).getType());
        assertEquals(2014, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());
        assertEquals(2, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(2, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(18, predicted.get(0).getTemporal().get(0).getStartDate().getTime().getHours());
        assertEquals(22, predicted.get(0).getTemporal().get(0).getEndDate().getTime().getHours());

    }
}
