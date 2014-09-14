package com.codeminders.labs.timeextractor.temporal.entites;

import com.codeminders.labs.timeextractor.constants.Type;

public class Temporal {

	private Type type;
	private Duration duration;
	private Set set;
	private TimeDate startDate;
	private TimeDate endDate;

	public Temporal() {

	}

	public Temporal(TimeDate startDate, TimeDate endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public TimeDate getStartDate() {
		return startDate;
	}

	public void setStartDate(TimeDate startDate) {
		this.startDate = startDate;
	}

	public TimeDate getEndDate() {
		return endDate;
	}

	public void setEndDate(TimeDate endDate) {
		this.endDate = endDate;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Temporal [type=" + type + ", duration=" + duration
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

}