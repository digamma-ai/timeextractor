package com.codeminders.labs.timeextractor.temporal.entities;

public class Temporal {

    private Type type;
    private Duration duration;
    private DurationInterval durationInterval;
    private Set set;

    private TimeDate startDate;
    private TimeDate endDate;

    public Temporal() {

    }

    public Temporal(TimeDate startDate, TimeDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Temporal(TimeDate startDate, TimeDate endDate, Type type) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public TimeDate getStartDate() {
        return startDate;
    }

    public void setStartDate(TimeDate startDate) {
        this.startDate = startDate;
    }

    public TimeDate getEndDate() {
        return endDate;
    }

    public void setEndDate(TimeDate endDate) {
        this.endDate = endDate;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public DurationInterval getDurationInterval() {
        return durationInterval;
    }

    public void setDurationInterval(DurationInterval durationInterval) {
        this.durationInterval = durationInterval;
    }

    @Override
    public String toString() {
        return "Temporal [type=" + type + ", duration=" + duration + ", durationInterval=" + durationInterval + ", set=" + set + ", startDate=" + startDate + ", endDate=" + endDate + "]";
    }

}