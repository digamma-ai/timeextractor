package com.codemonders.labs.timeextractor.rules.dates;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.date.MonthAndDayRule1;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;

public class MonthAndDayRule1Test {

    @Test
    public void getTemporalTest1() {
        String temporalExpression = "July the 14th 2014";
        MonthAndDayRule1 monthAndDayRule1 = new MonthAndDayRule1(temporalExpression);
        Temporal temporal = monthAndDayRule1.getTemporal().get(0);
        assertEquals(Type.DATE, temporal.getType());
        assertEquals(2014, temporal.getStartDate().getDate().getYear());
        assertEquals(7, temporal.getStartDate().getDate().getMonth());
        assertEquals(14, temporal.getStartDate().getDate().getDay());

    }

    @Test
    public void getTemporalTest2() {
        String temporalExpression = "July 14th 2014";
        MonthAndDayRule1 monthAndDayRule1 = new MonthAndDayRule1(temporalExpression);
        Temporal temporal = monthAndDayRule1.getTemporal().get(0);
        assertEquals(Type.DATE, temporal.getType());
        assertEquals(2014, temporal.getStartDate().getDate().getYear());
        assertEquals(7, temporal.getStartDate().getDate().getMonth());
        assertEquals(14, temporal.getStartDate().getDate().getDay());

    }

    @Test
    public void getTemporalTest3() {
        String temporalExpression = "July 14 2014";
        MonthAndDayRule1 monthAndDayRule1 = new MonthAndDayRule1(temporalExpression);
        Temporal temporal = monthAndDayRule1.getTemporal().get(0);
        assertEquals(Type.DATE, temporal.getType());
        assertEquals(2014, temporal.getStartDate().getDate().getYear());
        assertEquals(7, temporal.getStartDate().getDate().getMonth());
        assertEquals(14, temporal.getStartDate().getDate().getDay());

    }

    @Test
    public void getTemporalTest4() {
        String temporalExpression = "July 14 ";
        MonthAndDayRule1 monthAndDayRule1 = new MonthAndDayRule1(temporalExpression);
        Temporal temporal = monthAndDayRule1.getTemporal().get(0);
        assertEquals(Type.DATE, temporal.getType());
        assertEquals(7, temporal.getStartDate().getDate().getMonth());
        assertEquals(14, temporal.getStartDate().getDate().getDay());

    }

    @Test
    public void getTemporalTest5() {
        String temporalExpression = "July the 14 ";
        MonthAndDayRule1 monthAndDayRule1 = new MonthAndDayRule1(temporalExpression);
        Temporal temporal = monthAndDayRule1.getTemporal().get(0);
        assertEquals(Type.DATE, temporal.getType());
        assertEquals(7, temporal.getStartDate().getDate().getMonth());
        assertEquals(14, temporal.getStartDate().getDate().getDay());

    }

    @Test
    public void getTemporalTest6() {
        String temporalExpression = "january the 31 ";
        MonthAndDayRule1 monthAndDayRule1 = new MonthAndDayRule1(temporalExpression);
        Temporal temporal = monthAndDayRule1.getTemporal().get(0);
        assertEquals(Type.DATE, temporal.getType());
        assertEquals(1, temporal.getStartDate().getDate().getMonth());
        assertEquals(31, temporal.getStartDate().getDate().getDay());

    }

}
