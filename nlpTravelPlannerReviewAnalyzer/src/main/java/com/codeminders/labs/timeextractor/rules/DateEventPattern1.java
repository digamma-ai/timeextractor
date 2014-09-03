package com.codeminders.labs.timeextractor.rules;

import java.util.Locale;

import edu.stanford.nlp.time.SUTime.Duration;
import edu.stanford.nlp.time.SUTime.Time;
import edu.stanford.nlp.time.SUTime.TimexType;

// 2012/09/01
public class DateEventPattern1 extends Time {

    private String everyEvent;
    public static String rule = "\b((19|20)\\d\\d)[-.\\/]((0[1-9]|1[012]))[-.\\/]((0[1-9]|[12][0-9]|3[01]))\b";
    private Locale locale;
    private int confidence;

    public DateEventPattern1(String everyEvent) {
        this.everyEvent = everyEvent;
    }

    public TimexType getTimexType() {
        return TimexType.DATE;
    }

    @Override
    public Time add(Duration offset) {
        // TODO Auto-generated method stub
        return null;
    }

    public String getTimexValue() {
        return "DateEventPattern1";
    }

    public String getTimeLabel() {
        return everyEvent;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

}