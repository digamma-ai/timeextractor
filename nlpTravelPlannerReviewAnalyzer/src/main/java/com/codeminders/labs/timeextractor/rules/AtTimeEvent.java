package com.codeminders.labs.timeextractor.rules;

import edu.stanford.nlp.time.SUTime.Duration;
import edu.stanford.nlp.time.SUTime.RelativeTime;
import edu.stanford.nlp.time.SUTime.TemporalOp;
import edu.stanford.nlp.time.SUTime.Time;

public class AtTimeEvent extends Time {
    
    private static final long serialVersionUID = 1;

    private String time;
    private String timeFormat;
    private String modifier;
    private String timeZone;

    public AtTimeEvent( String modifier, String time, String timeFormat, String timeZone) {
        this.time = time;
        this.timeFormat = timeFormat;
        this.modifier = modifier;
        this.timeZone = timeZone;
    }

    public String toFormattedString(int flags) {
        if (getTimeLabel() != null) {
            return getTimeLabel();
        }
        return time + timeFormat;
    }

    public Time add(Duration offset) {
        Time t = new RelativeTime(this, TemporalOp.OFFSET_EXACT, offset);
        return t;
    };

}