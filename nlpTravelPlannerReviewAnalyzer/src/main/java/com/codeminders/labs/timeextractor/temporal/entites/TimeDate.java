package com.codeminders.labs.timeextractor.temporal.entites;

public class TimeDate {

	private Time time;
	private Date date;
	private Set set;

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

	public Set getSet() {
		return set;
	}

	public void setSet(Set set) {
		this.set = set;
	}

	@Override
	public String toString() {
		return "TimeDate [time=" + time + ", date=" + date + ", set=" + set
				+ "]";
	}

}
