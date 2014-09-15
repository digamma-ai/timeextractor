package com.codeminders.labs.timeextractor.temporal.entites;

import com.codeminders.labs.timeextractor.constants.DaysOfRepetition;
import com.codeminders.labs.timeextractor.constants.Frequency;

public class Set {

    private Frequency frequency;
    private DaysOfRepetition daysOfRepetition;
    private int interval;

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

    @Override
    public String toString() {
        return "Set [frequency=" + frequency + ", daysOfRepetition=" + daysOfRepetition + ", interval=" + interval + "]";
    }

}
