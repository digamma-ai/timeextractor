package com.codeminders.labs.timeextractor.sutime;

import java.util.Map;

import edu.stanford.nlp.time.SUTime.Duration;
import edu.stanford.nlp.time.SUTime.Time;
import edu.stanford.nlp.time.SUTime.TimexType;

public class EveryEvent extends Time {

    private String everyEvent;

    public EveryEvent(String everyEvent) {
        this.everyEvent = everyEvent;
    }

    public TimexType getTimexType() {
        return TimexType.SET;
    }

    @Override
    public Time add(Duration offset) {
        // TODO Auto-generated method stub
        return null;
    }

    public String getTimexValue() {
        return "custom";
    }

    public String getTimeLabel() {
        return everyEvent;
    }

}
