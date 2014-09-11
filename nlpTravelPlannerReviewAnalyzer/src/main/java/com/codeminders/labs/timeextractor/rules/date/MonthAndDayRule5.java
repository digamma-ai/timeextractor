package com.codeminders.labs.timeextractor.rules.date;

import java.util.List;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;

// Sunday 17 of July
public class MonthAndDayRule5 extends BaseRule {
	private String dayOfWeek;
	private String day;
	private String month;

	public MonthAndDayRule5(String dayOfWeek, String day, String month) {
		this.dayOfWeek = dayOfWeek;
		this.day = day;
		this.month = month;
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Temporal> getTemporal() {
		// TODO Auto-generated method stub
		return null;
	}

}
