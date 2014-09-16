package com.codeminders.labs.timeextractor.rules.dateinterval;

import java.util.ArrayList;
import java.util.List;

import com.codeminders.labs.timeextractor.constants.MonthOfYear;
import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Date;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.TimeDate;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;

public class Dateinterval2 extends BaseRule {
    private String dateFrom;
    private String dateTo;
    private String monthFrom;
    private String monthTo;
    private String year;

    public Dateinterval2(String dateFrom, String monthFrom, String dateTo, String monthTo, String year) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.monthFrom = monthFrom;
        this.monthTo = monthTo;
        this.year = year;
    }

    @Override
    public Type getType() {
        return Type.DATE_INTERVAL;
    }

    @Override
    public List<Temporal> getTemporal() {
        int startDay = Integer.parseInt(this.dateFrom);
        int endDay = Integer.parseInt(this.dateTo);
        int monthFrom = 0;
        int monthTo = 0;

        int year = 0;
        MonthOfYear monthEnumFrom = TemporalBasicCaseParser.getMonthOfYear(this.monthFrom);
        if (monthEnumFrom != null) {
            monthFrom = monthEnumFrom.getValue();
        }
        MonthOfYear monthEnumTo = TemporalBasicCaseParser.getMonthOfYear(this.monthTo);
        if (monthEnumTo != null) {
            monthTo = monthEnumTo.getValue();
        }
        if (this.year != null) {
            year = Integer.parseInt(this.year);
        }
        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        Date startDate = new Date();
        Date endDate = new Date();

        startDate.setDay(startDay);
        startDate.setMonth(monthFrom);
        startDate.setYear(year);
        endDate.setDay(endDay);
        endDate.setMonth(monthTo);
        endDate.setYear(year);

        start.setDate(startDate);
        end.setDate(endDate);

        Temporal temporal = TemporalObjectGenerator.generateTemporalTime(Type.DATE_INTERVAL, start, end);
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);
        return temporalList;
    }

}
