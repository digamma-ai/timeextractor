package com.codeminders.labs.timeextractor.temporal.entites;

public class Time {

    private int hours;
    private int minutes;
    private int seconds;
    private int timezone;

    public Time() {
    }

    public Time(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
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

    public int getTimezoneOffset() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    @Override
    public String toString() {
        return "Time [hours=" + hours + ", minutes=" + minutes + ", seconds=" + seconds + ", timezoneOffset=" + timezone + "]";
    }

}
