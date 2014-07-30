package com.codeminders.labs.timeextractor.sutime;

import edu.stanford.nlp.time.SUTime.Duration;
import edu.stanford.nlp.time.SUTime.RelativeTime;
import edu.stanford.nlp.time.SUTime.TemporalOp;
import edu.stanford.nlp.time.SUTime.Time;

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

        return label;
    }

    public Time add(Duration offset) {
        Time t = new RelativeTime(this, TemporalOp.OFFSET_EXACT, offset);
        return t;
    };

}
