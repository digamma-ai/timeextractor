package com.codeminders.labs.timeextractor.temporal.entites;

public class TimeDate {

	private Time time;
	private Date date;
	private boolean relative;

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isRelative() {
		return relative;
	}

	public void setRelative(boolean relative) {
		this.relative = relative;
	}

	@Override
	public String toString() {
		return "TimeDate [time=" + time + ", date=" + date + "]";
	}

}
