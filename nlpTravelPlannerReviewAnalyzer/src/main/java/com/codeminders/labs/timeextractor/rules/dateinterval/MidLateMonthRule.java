package com.codeminders.labs.timeextractor.rules.dateinterval;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import com.codeminders.labs.timeextractor.constants.MonthOfYear;
import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Date;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.TimeDate;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;

public class MidLateMonthRule extends BaseRule {
    private String month;

    public MidLateMonthRule(String month) {
        this.month = month;
    }

    @Override
    public Type getType() {
        return Type.DATEINTERVAL;
    }

    @Override
    public List<Temporal> getTemporal() {
        MonthOfYear currentMonth = TemporalBasicCaseParser.getMonthOfYear(this.month);
        int maxDay = 0;
        int month = 0;

        if (currentMonth != null) {
            month = currentMonth.getValue();
            LocalDate date = new LocalDate(new LocalDate().getYear(), month, 1);
            maxDay = date.dayOfMonth().withMaximumValue().getDayOfMonth();
        }

        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        Date startDate = new Date();
        Date endDate = new Date();

        start.setRelative(true);
        end.setRelative(true);

        startDate.setMonth(month);
        startDate.setDay(maxDay / 2);
        endDate.setMonth(month);
        endDate.setDay(maxDay);

        start.setDate(startDate);
        end.setDate(endDate);

        Temporal temporal = TemporalObjectGenerator.generateTemporalTime(start, end);
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);

        return temporalList;
    }

}
