package com.codemonders.labs.timeextractor.rules.dates;

import static org.junit.Assert.*;

import org.junit.Test;

import com.codeminders.labs.timeextractor.constants.DayOfWeek;
import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.date.DayOfWeekRule1;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;

public class DayOfWeekRule1Test {

    @Test
    public void getTemporalTestMonday() {
        String temporalExpression = "Monday";
        DayOfWeekRule1 dayOfWeekRule = new DayOfWeekRule1(temporalExpression);
        Temporal temporal = dayOfWeekRule.getTemporal().get(0);
        assertEquals(Type.DATE, temporal.getType());
        assertEquals(DayOfWeek.MONDAY, temporal.getStartDate().getDate().getDayOfWeek());
    }

    @Test
    public void getTemporalTestMonday2() {
        String temporalExpression = "Mon";
        DayOfWeekRule1 dayOfWeekRule = new DayOfWeekRule1(temporalExpression);
        Temporal temporal = dayOfWeekRule.getTemporal().get(0);
        assertEquals(Type.DATE, temporal.getType());
        assertEquals(DayOfWeek.MONDAY, temporal.getStartDate().getDate().getDayOfWeek());
    }

    @Test
    public void getTemporalTestTuesday() {
        String temporalExpression = "Tuesday";
        DayOfWeekRule1 dayOfWeekRule = new DayOfWeekRule1(temporalExpression);
        Temporal temporal = dayOfWeekRule.getTemporal().get(0);
        assertEquals(DayOfWeek.TUESDAY, temporal.getStartDate().getDate().getDayOfWeek());

    }

    @Test
    public void getTemporalTestTuesday2() {
        String temporalExpression = "Tue";
        DayOfWeekRule1 dayOfWeekRule = new DayOfWeekRule1(temporalExpression);
        Temporal temporal = dayOfWeekRule.getTemporal().get(0);
        assertEquals(DayOfWeek.TUESDAY, temporal.getStartDate().getDate().getDayOfWeek());

    }

    @Test
    public void getTemporalTestWednesday() {
        String temporalExpression = "Wednesday";
        DayOfWeekRule1 dayOfWeekRule = new DayOfWeekRule1(temporalExpression);
        Temporal temporal = dayOfWeekRule.getTemporal().get(0);
        assertEquals(DayOfWeek.WEDNESDAY, temporal.getStartDate().getDate().getDayOfWeek());

    }

    @Test
    public void getTemporalTestWednesday2() {
        String temporalExpression = "Wed";
        DayOfWeekRule1 dayOfWeekRule = new DayOfWeekRule1(temporalExpression);
        Temporal temporal = dayOfWeekRule.getTemporal().get(0);
        assertEquals(DayOfWeek.WEDNESDAY, temporal.getStartDate().getDate().getDayOfWeek());

    }

    @Test
    public void getTemporalTestThursday() {
        String temporalExpression = "Thursday";
        DayOfWeekRule1 dayOfWeekRule = new DayOfWeekRule1(temporalExpression);
        Temporal temporal = dayOfWeekRule.getTemporal().get(0);
        assertEquals(DayOfWeek.THURSDAY, temporal.getStartDate().getDate().getDayOfWeek());

    }

    @Test
    public void getTemporalTestThursday2() {
        String temporalExpression = "Thur";
        DayOfWeekRule1 dayOfWeekRule = new DayOfWeekRule1(temporalExpression);
        Temporal temporal = dayOfWeekRule.getTemporal().get(0);
        assertEquals(DayOfWeek.THURSDAY, temporal.getStartDate().getDate().getDayOfWeek());

    }

    @Test
    public void getTemporalTestFriday() {
        String temporalExpression = "Friday";
        DayOfWeekRule1 dayOfWeekRule = new DayOfWeekRule1(temporalExpression);
        Temporal temporal = dayOfWeekRule.getTemporal().get(0);
        assertEquals(DayOfWeek.FRIDAY, temporal.getStartDate().getDate().getDayOfWeek());

    }

    @Test
    public void getTemporalTestFriday2() {
        String temporalExpression = "Fri";
        DayOfWeekRule1 dayOfWeekRule = new DayOfWeekRule1(temporalExpression);
        Temporal temporal = dayOfWeekRule.getTemporal().get(0);
        assertEquals(DayOfWeek.FRIDAY, temporal.getStartDate().getDate().getDayOfWeek());

    }

    @Test
    public void getTemporalTestSaturday() {
        String temporalExpression = "Saturday";
        DayOfWeekRule1 dayOfWeekRule = new DayOfWeekRule1(temporalExpression);
        Temporal temporal = dayOfWeekRule.getTemporal().get(0);
        assertEquals(DayOfWeek.SATURDAY, temporal.getStartDate().getDate().getDayOfWeek());

    }

    @Test
    public void getTemporalTestSaturday2() {
        String temporalExpression = "Sat";
        DayOfWeekRule1 dayOfWeekRule = new DayOfWeekRule1(temporalExpression);
        Temporal temporal = dayOfWeekRule.getTemporal().get(0);
        assertEquals(DayOfWeek.SATURDAY, temporal.getStartDate().getDate().getDayOfWeek());

    }

    @Test
    public void getTemporalTestSunday() {
        String temporalExpression = "Sunday";
        DayOfWeekRule1 dayOfWeekRule = new DayOfWeekRule1(temporalExpression);
        Temporal temporal = dayOfWeekRule.getTemporal().get(0);
        assertEquals(DayOfWeek.SUNDAY, temporal.getStartDate().getDate().getDayOfWeek());

    }

    @Test
    public void getTemporalTestSunday2() {
        String temporalExpression = "Sun";
        DayOfWeekRule1 dayOfWeekRule = new DayOfWeekRule1(temporalExpression);
        Temporal temporal = dayOfWeekRule.getTemporal().get(0);
        assertEquals(DayOfWeek.SUNDAY, temporal.getStartDate().getDate().getDayOfWeek());

    }

}
