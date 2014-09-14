package com.codeminders.labs.timeextractor.rules.dateinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.constants.DayOfWeek;
import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Date;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.TimeDate;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;

public class DayOfWeekIntervalRule1 extends BaseRule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.83;
    private String dayOfWeek1;
    private String dayOfWeek2;

    public DayOfWeekIntervalRule1(String dayOfWeek1, String dayOfWeek2) {
        this.dayOfWeek1 = dayOfWeek1;
        this.dayOfWeek2 = dayOfWeek2;
    }

    @Override
    public Type getType() {
        return Type.DATEINTERVAL;
    }

    @Override
    public List<Temporal> getTemporal() {
        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        Date startDate = new Date();
        Date endDate = new Date();

        DayOfWeek startDayOfWeek = TemporalBasicCaseParser.getDayOfWeek((this.dayOfWeek1));
        DayOfWeek endDayOfWeek = TemporalBasicCaseParser.getDayOfWeek((this.dayOfWeek2));

        if (startDayOfWeek != null && endDayOfWeek != null) {
            startDate.setDayOfWeek(startDayOfWeek);
            endDate.setDayOfWeek(endDayOfWeek);

        }

        start.setDate(startDate);
        end.setDate(endDate);
        Temporal temporal = TemporalObjectGenerator.generateTemporalTime(Type.DATEINTERVAL, start, end);
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);
        return temporalList;
    }
}
