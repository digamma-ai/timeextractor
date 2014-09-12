package com.codeminders.labs.timeextractor.rules.timeinterval;

import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;

public class TimeIntervalRule3 extends BaseRule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.83;
    private String hours;
    private String minutes;
    private String timezone;
    private String tag;

    public TimeIntervalRule3(String tag, String hours, String minutes, String timezone) {
        this.tag = tag;
        this.hours = hours;
        this.minutes = minutes;
        this.timezone = timezone;
    }

    public TimeIntervalRule3(String tag, String hours, String timezone) {
        this.tag = tag;
        this.hours = hours;
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
