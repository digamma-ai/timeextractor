package com.codeminders.labs.timeextractor.rules.timeinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.Time;
import com.codeminders.labs.timeextractor.temporal.entites.TimeDate;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;
import com.codeminders.labs.timeextractor.utils.TimeConvertor;

public class TimeIntervalRule5 extends BaseRule {
    private TemporalBasicCaseParser parser;

    protected Locale locale = Locale.US;
    protected double confidence = 0.83;
    private String fromHours;
    private String fromMinutes;
    private String fromPmAm;
    private String toHours;
    private String toMinutes;
    private String toPmAm;
    private String timezone;

    {
        parser = new TemporalBasicCaseParser();
    }

    public TimeIntervalRule5(String fromHours, String fromMinutes, String fromPmAm, String toHours, String toMinutes, String toPmAm, String timezone) {
        this.fromHours = fromHours;
        this.fromMinutes = fromMinutes;
        this.fromPmAm = fromPmAm;
        this.toHours = toHours;
        this.toMinutes = toMinutes;
        this.toPmAm = toPmAm;
        this.timezone = timezone;

    }

    @Override
    public Type getType() {
        return Type.TIME_INTERVAL;

    }

    @Override
    public List<Temporal> getTemporal() {
        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        Time timeFrom = new Time();
        Time timeTo = new Time();

        Temporal temporal = null;
        int timezone = 0;

        if (this.fromHours != null) {
            timeFrom.setHours(Integer.parseInt(this.fromHours));
            if (this.fromPmAm != null) {
                timeFrom.setHours(TimeConvertor.convertTime(timeFrom.getHours(), fromPmAm));
            }
        }
        if (this.fromMinutes != null) {
            timeFrom.setMinutes(Integer.parseInt(this.fromMinutes));
        }

        if (this.toHours != null) {
            timeTo.setHours(Integer.parseInt(this.toHours));
            if (this.toPmAm != null) {
                timeTo.setHours(TimeConvertor.convertTime(timeTo.getHours(), toPmAm));
            }
        }

        if (this.toMinutes != null) {
            timeTo.setMinutes(Integer.parseInt(this.toMinutes));
        }

        if (this.timezone != null) {
            timezone = parser.getTimeZone(this.timezone);
            timeTo.setTimezone(timezone);
            timeFrom.setTimezone(timezone);
        }

        start.setTime(timeFrom);
        end.setTime(timeTo);

        temporal = TemporalObjectGenerator.generateTemporalTime(Type.TIME_INTERVAL, start, end);
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);
        return temporalList;
    }

}
