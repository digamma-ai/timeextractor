package ai.digamma.dto;

import java.util.List;

import ai.digamma.temporal.entities.DaysOfRepetition;
import ai.digamma.temporal.entities.Frequency;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.temporal.entities.TimeDate;
import ai.digamma.temporal.entities.DayOfWeek;

public class DTOSet implements DTOTemporal {

    private TimeDate startDateTime;
    private TimeDate endDateTime;
    private Frequency frequency;
    private DaysOfRepetition daysOfRepetition;
    private List<DayOfWeek> byDay;
    private int interval;

    public DTOSet(Temporal temporal) {

        this.startDateTime = temporal.getStartDate();
        this.endDateTime = temporal.getEndDate();
        if (temporal.getSet() != null) {
            frequency = temporal.getSet().getFrequency();
            daysOfRepetition = temporal.getSet().getDaysOfRepetition();
            interval = temporal.getSet().getInterval();
            byDay = temporal.getSet().getByDay();
        }
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

    public List<DayOfWeek> getByDay() {
        return byDay;
    }

    public void setByDay(List<DayOfWeek> byDay) {
        this.byDay = byDay;
    }

}
