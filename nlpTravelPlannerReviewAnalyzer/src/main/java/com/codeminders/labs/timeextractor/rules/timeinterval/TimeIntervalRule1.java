package com.codeminders.labs.timeextractor.rules.timeinterval;

import java.util.List;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;

public class TimeIntervalRule1 extends BaseRule {

    private String time;
    private String timeZone;
    private String tag;

    public TimeIntervalRule1(String tag, String time, String timeZone) {
        this.tag = tag;
        this.time = time;
        this.timeZone = timeZone;
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
