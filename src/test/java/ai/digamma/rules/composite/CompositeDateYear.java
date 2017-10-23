package ai.digamma.rules.composite;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import ai.digamma.rules.general.GeneralTest;
import ai.digamma.service.TimeExtractor;
import org.junit.Test;

import ai.digamma.entities.TemporalExtraction;
import ai.digamma.temporal.entities.Date;
import ai.digamma.temporal.entities.Type;

public class CompositeDateYear extends GeneralTest {

    @Test
    public void compositeDateTimeInterval1() {

        String toPredict = "25th of May 2014";
        List<TemporalExtraction> predicted =  new ArrayList<>(TimeExtractor.extract(toPredict,settings));
        Type type = predicted.get(0).getTemporal().get(0).getType();
        Date startDate = predicted.get(0).getTemporal().get(0).getStartDate().getDate();
        Date endDate = predicted.get(0).getTemporal().get(0).getEndDate().getDate();

        assertEquals("25th of May 2014", predicted.get(0).getTemporalExpression());
        assertEquals(Type.DATE, type);
        assertEquals(2014, startDate.getYear());
        assertEquals(5, startDate.getMonth());
        assertEquals(25, startDate.getDay());
        assertEquals(2014, endDate.getYear());
        assertEquals(5, endDate.getMonth());
        assertEquals(25, endDate.getDay());

    }
}
