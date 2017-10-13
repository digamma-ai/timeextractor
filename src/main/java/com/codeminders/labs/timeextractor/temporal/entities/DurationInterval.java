package com.codeminders.labs.timeextractor.temporal.entities;

public class DurationInterval {

    private Duration durationFrom;
    private Duration durationTo;

    public DurationInterval() {
    }

    public DurationInterval(Duration durationFrom, Duration durationTo) {
        super();
        this.durationFrom = durationFrom;
        this.durationTo = durationTo;
    }

    public Duration getDurationFrom() {
        return durationFrom;
    }

    public void setDurationFrom(Duration durationFrom) {
        this.durationFrom = durationFrom;
    }

    public Duration getDurationTo() {
        return durationTo;
    }

    public void setDurationTo(Duration durationTo) {
        this.durationTo = durationTo;
    }

    @Override
    public String toString() {
        return "DurationInterval [durationFrom=" + durationFrom + ", durationTo=" + durationTo + "]";
    }

}
