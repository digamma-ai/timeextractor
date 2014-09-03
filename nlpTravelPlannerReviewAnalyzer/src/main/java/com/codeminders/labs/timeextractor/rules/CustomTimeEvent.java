package com.codeminders.labs.timeextractor.rules;

import edu.stanford.nlp.time.SUTime.Duration;
import edu.stanford.nlp.time.SUTime.Time;
import edu.stanford.nlp.time.SUTime.TimexType;

public class CustomTimeEvent extends Time {
    private static final long serialVersionUID = 1;

    String label;

    public CustomTimeEvent(String label) {
        this.label = label;
    }

    public String toFormattedString(int flags) {
        if (getTimeLabel() != null) {
            return getTimeLabel();
        }

        return "test";
    }

    public Time add(Duration offset) {
        // Time t = new RelativeTime(this, TemporalOp.OFFSET_EXACT, offset);
        return null;
    };



}