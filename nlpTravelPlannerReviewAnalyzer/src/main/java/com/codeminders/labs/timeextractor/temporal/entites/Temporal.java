package com.codeminders.labs.timeextractor.temporal.entites;

import com.codeminders.labs.timeextractor.constants.Type;

public class Temporal {

	private Type type;
	private TimeDate startDate;
	private TimeDate endDate;

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

	@Override
	public String toString() {
		return "Temporal [type=" + type + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}

}