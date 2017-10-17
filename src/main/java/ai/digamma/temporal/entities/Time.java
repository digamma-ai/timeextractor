package ai.digamma.temporal.entities;

public class Time {

    private int hours;
    private int minutes;
    private int seconds;
    private int timezone = -1000;
    private String timezoneName;

    public Time() {
    }

    public Time(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public Time(int hours, int minutes, int seconds, int timezone) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.timezone = timezone;
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

    public String getTimezoneName() {
        return timezoneName;
    }

    public void setTimezoneName(String timezoneName) {
        this.timezoneName = timezoneName;
    }

    @Override
    public String toString() {
        return "Time [hours=" + hours + ", minutes=" + minutes + ", seconds=" + seconds + ", timezoneOffset=" + timezone + "]";
    }

}
