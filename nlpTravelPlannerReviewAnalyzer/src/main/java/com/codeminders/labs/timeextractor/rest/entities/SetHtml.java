package com.codeminders.labs.timeextractor.rest.entities;

import com.codeminders.labs.timeextractor.constants.DaysOfRepetition;
import com.codeminders.labs.timeextractor.constants.Frequency;
import com.codeminders.labs.timeextractor.temporal.entites.Set;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.TimeDate;

public class SetHtml implements HtmlTemporal {

    private TimeDate startDateTime;
    private TimeDate endDateTime;
    private Frequency frequency;
    private DaysOfRepetition daysOfRepetition;
    private int interval;

    public SetHtml(Temporal temporal) {

        this.startDateTime = temporal.getStartDate();
        this.endDateTime = temporal.getEndDate();
        frequency = temporal.getSet().getFrequency();
        daysOfRepetition = temporal.getSet().getDaysOfRepetition();
        interval = temporal.getSet().getInterval();
    }

    public TimeDate getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(TimeDate startDateTime) {
        this.startDateTime = startDateTime;
    }

    public TimeDate getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(TimeDate endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public DaysOfRepetition getDaysOfRepetition() {
        return daysOfRepetition;
    }

    public void setDaysOfRepetition(DaysOfRepetition daysOfRepetition) {
        this.daysOfRepetition = daysOfRepetition;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

}
