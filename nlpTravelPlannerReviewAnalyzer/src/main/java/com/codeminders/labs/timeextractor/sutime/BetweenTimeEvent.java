package com.codeminders.labs.timeextractor.sutime;

import org.joda.time.Partial;

import edu.stanford.nlp.time.SUTime;
import edu.stanford.nlp.time.SUTime.Duration;
import edu.stanford.nlp.time.SUTime.Range;
import edu.stanford.nlp.time.SUTime.RelativeTime;
import edu.stanford.nlp.time.SUTime.TemporalOp;
import edu.stanford.nlp.time.SUTime.Time;
import edu.stanford.nlp.time.SUTime.TimexType;

public class BetweenTimeEvent extends Time {
    private static final long serialVersionUID = 1;
    String values;

    public BetweenTimeEvent(String label) {
        this.values = label;
    }

    public String toFormattedString(int flags) {
        if (getTimeLabel() != null) {
            return getTimeLabel();
        }
        return values;
    }

    public TimexType getTimexType() {
        return TimexType.DURATION;
    }

    @Override
    public Duration getDuration() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Range getRange(int flags, Duration granularity) {
        Range range = new Range(new SUTime.PartialTime(), new SUTime.PartialTime());
        return range;
    }

    public Time add(Duration offset) {
        Time t = new RelativeTime(this, TemporalOp.OFFSET_EXACT, offset);
        return t;
    };

    public org.joda.time.Interval getJodaTimeInterval() {
        return null;
    }

}
