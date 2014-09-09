package com.codeminders.labs.timeextractor.rules.dateinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.constants.MonthOfYear;
import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Date;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.TimeDate;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;

public class MonthDaysIntervalRule1 extends BaseRule {

	protected Locale locale = Locale.US;
	protected double confidence = 0.83;
	private String month;
	private String startDay;
	private String endDay;
	private String year;

	public MonthDaysIntervalRule1(String month, String startDay, String endDay,
			String year) {
		this.month = month;
		this.startDay = startDay;
		this.endDay = endDay;
		this.year = year;
	}

	@Override
	public Type getType() {
		return Type.DATEINTERVAL;
	}

	@Override
	public List<Temporal> getTemporal() {
		int startDay = Integer.parseInt(this.startDay);
		int endDay = Integer.parseInt(this.endDay);
		int month = 0;
		int year = 0;
		MonthOfYear monthEnum = TemporalBasicCaseParser
				.getMonthOfYear(this.month);
		if (monthEnum != null) {
			month = monthEnum.getValue();
		}
		if (this.year != null) {
			year = Integer.parseInt(this.year);
		}
		TimeDate start = new TimeDate();
		TimeDate end = new TimeDate();

		Date startDate = new Date();
		Date endDate = new Date();

		startDate.setDay(startDay);
		startDate.setMonth(month);
		startDate.setYear(year);
		endDate.setDay(endDay);
		endDate.setMonth(month);
		endDate.setYear(year);

		start.setDate(startDate);
		end.setDate(endDate);

		Temporal temporal = TemporalObjectGenerator.generateTemporalTime(start,
				end);
		List<Temporal> temporalList = new ArrayList<Temporal>();
		temporalList.add(temporal);
		return temporalList;
	}
}
