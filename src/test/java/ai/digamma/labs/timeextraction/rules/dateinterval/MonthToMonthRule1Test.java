package ai.digamma.labs.timeextraction.rules.dateinterval;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import ai.digamma.rules.general.GeneralTest;
import ai.digamma.service.DateTimeExtractor;
import org.junit.Test;

import ai.digamma.entities.TemporalExtraction;

public class MonthToMonthRule1Test extends GeneralTest {

    @Test
    public void monthToMonthRuleTest1() {

        String toPredict = "April through October 2014";
        List<TemporalExtraction> predicted =  new ArrayList<>(DateTimeExtractor.extract(toPredict,settings));
        assertEquals("April through October 2014", predicted.get(0).getTemporalExpression());
        assertEquals(4, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(10, predicted.get(0).getTemporal().get(0).getEndDate().getDate().getMonth());
        assertEquals(2014, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());
        assertEquals(2014, predicted.get(0).getTemporal().get(0).getEndDate().getDate().getYear());
    }
}
