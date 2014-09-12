package com.codeminders.labs.timeextractor.rules.timeinterval;

import java.util.List;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;

public class TimeIntervalRule2 extends BaseRule {

    private String timeOfDay1;
    private String timeOfDay2;

    public TimeIntervalRule2(String timeOfDay1, String timeOfDay2) {
        this.timeOfDay1 = timeOfDay1;
        this.timeOfDay2 = timeOfDay2;
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