package ai.digamma.labs.timeextraction.rules.dateinterval;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import ai.digamma.rules.general.GeneralTest;
import ai.digamma.service.DateTimeExtractor;
import org.junit.Test;

import ai.digamma.entities.TemporalExtraction;

public class SeasonRulesTest extends GeneralTest {

    @Test
    public void seasonRulesTest1() {

        String toPredict = "spring";
        List<TemporalExtraction> predicted =  new ArrayList<>(DateTimeExtractor.extract(toPredict,settings));
        assertEquals("spring", predicted.get(0).getTemporalExpression());
        assertEquals(3, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(5, predicted.get(0).getTemporal().get(0).getEndDate().getDate().getMonth());

    }

    @Test
    public void seasonRulesTest2() {

        String toPredict = "winter";
        List<TemporalExtraction> predicted =  new ArrayList<>(DateTimeExtractor.extract(toPredict,settings));
        assertEquals("winter", predicted.get(0).getTemporalExpression());
        assertEquals(12, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(2, predicted.get(0).getTemporal().get(0).getEndDate().getDate().getMonth());

    }

    @Test
    public void seasonRulesTest3() {

        String toPredict = "summer";
        List<TemporalExtraction> predicted =  new ArrayList<>(DateTimeExtractor.extract(toPredict,settings));
        assertEquals("summer", predicted.get(0).getTemporalExpression());
        assertEquals(6, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(8, predicted.get(0).getTemporal().get(0).getEndDate().getDate().getMonth());

    }

    @Test
    public void seasonRulesTest4() {

        String toPredict = "autumn";
        List<TemporalExtraction> predicted =  new ArrayList<>(DateTimeExtractor.extract(toPredict,settings));
        assertEquals("autumn", predicted.get(0).getTemporalExpression());
        assertEquals(9, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(11, predicted.get(0).getTemporal().get(0).getEndDate().getDate().getMonth());

    }

}
