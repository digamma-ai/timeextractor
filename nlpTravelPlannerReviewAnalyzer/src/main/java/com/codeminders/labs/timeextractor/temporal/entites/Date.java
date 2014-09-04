package com.codeminders.labs.timeextractor.temporal.entites;

import com.codeminders.labs.timeextractor.constants.DayOfWeek;

public class Date {

	private int year;
	private int month;
	private int day;
	private DayOfWeek dayOfWeek;
	private int weekOfMonth;

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

	public int getWeekOfMonth() {
		return weekOfMonth;
	}

	public void setWeekOfMonth(int weekOfMonth) {
		this.weekOfMonth = weekOfMonth;
	}

	@Override
	public String toString() {
		return "Date [year=" + year + ", month=" + month + ", day=" + day
				+ ", dayOfWeek=" + dayOfWeek + ", weekOfMonth=" + weekOfMonth
				+ "]";
	}

}
