package com.codeminders.labs.timeextractor.rules.date;

import java.util.List;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;

public class MonthDayYearRule1 extends BaseRule {

	private String month;
	private String day;
	private String year;

	public MonthDayYearRule1(String day, String month, String year) {
		super();
		this.month = month;
		this.day = day;
		this.year = year;
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
