package com.codeminders.labs.timeextractor.rules.timeinterval;

import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;

public class TimeIntervalRule4 extends BaseRule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.83;
    private String hours;
    private String minutes;
    private String pMAm;
    private String timezone;
    private String tag;

    public TimeIntervalRule4(String tag, String hours, String minutes, String pMAm, String timezone) {
        this.tag = tag;
        this.hours = hours;
        this.minutes = minutes;
        this.pMAm = pMAm;
        this.timezone = timezone;
    }

    public TimeIntervalRule4(String tag, String hours, String pMAm, String timezone) {
        this.tag = tag;
        this.hours = hours;
        this.pMAm = pMAm;
        this.timezone = timezone;
    }

    @Override
    public Type getType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Temporal> getTemporal() {
        // TODO Auto-generated method stub
        return null;
    }

}
