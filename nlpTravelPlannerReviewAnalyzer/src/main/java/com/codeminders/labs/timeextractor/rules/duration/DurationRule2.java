package com.codeminders.labs.timeextractor.rules.duration;

import java.util.List;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;

public class DurationRule2 extends BaseRule {
    private String durationOrder;
    private String durationPeriod;

    public DurationRule2(String durationOrder, String durationPeriod) {
        this.durationOrder = durationOrder;
        this.durationPeriod = durationPeriod;
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
