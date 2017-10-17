package ai.digamma.temporal.entities;

public class Duration {

    private int days;
    private int hours;
    private int minutes;
    private int seconds;
    private int years;
    private int weeks;
    private int months;

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
