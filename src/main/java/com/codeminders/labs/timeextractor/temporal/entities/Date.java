package com.codeminders.labs.timeextractor.temporal.entities;

public class Date {

    private int year;
    private int month;
    private int day;
    private DayOfWeek dayOfWeek;
    private WeekOfMonth weekOfMonth;

    public Date() {
    }

    public Date(int year, int month, int day, DayOfWeek dayOfWeek, WeekOfMonth weekOfMonth) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.dayOfWeek = dayOfWeek;
        this.weekOfMonth = weekOfMonth;
    }

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Date(int month, int day) {
        super();
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setWeekOfMonth(WeekOfMonth weekOfMonth2) {
        this.weekOfMonth = weekOfMonth2;
    }

    public WeekOfMonth getWeekOfMonth() {
        return weekOfMonth;
    }

    @Override
    public String toString() {
        return "Date [year=" + year + ", month=" + month + ", day=" + day + ", dayOfWeek=" + dayOfWeek + ", weekOfMonth=" + weekOfMonth + "]";
    }

}
