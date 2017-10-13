package com.codeminders.labs.timeextractor.dto;

import com.codeminders.labs.timeextractor.temporal.entities.Duration;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;

public class DTODuration implements DTOTemporal {
    private int seconds;
    private int minutes;
    private int hours;
    private int days;
    private int weeks;
    private int months;
    private int years;

    public DTODuration(Temporal temporal) {
        days = temporal.getDuration().getDays();
        hours = temporal.getDuration().getHours();
        minutes = temporal.getDuration().getMinutes();
        seconds = temporal.getDuration().getSeconds();
        years = temporal.getDuration().getYears();
        weeks = temporal.getDuration().getWeeks();
        months = temporal.getDuration().getMonths();
    }

    public DTODuration(Duration duration) {
        days = duration.getDays();
        hours = duration.getHours();
        minutes = duration.getMinutes();
        seconds = duration.getSeconds();
        years = duration.getYears();
        weeks = duration.getWeeks();
        months = duration.getMonths();
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getWeeks() {
        return weeks;
    }

    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    @Override
    public String toString() {
        return "Duration [days=" + days + ", hours=" + hours + ", minutes=" + minutes + ", seconds=" + seconds + ", years=" + years + ", weeks=" + weeks + ", months=" + months + "]";
    }

}
