package com.codeminders.labs.timeextractor.rules.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.constants.DayOfWeek;
import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Date;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;

public class DayOfWeekRule2 extends BaseRule {

	protected Locale locale = Locale.US;
	protected double confidence = 0.83;
	private String dayOfWeek;
	private String dayOfMonth;

	public DayOfWeekRule2(String dayOfWeek, String dayOfMonth) {
		this.dayOfWeek = dayOfWeek;
		this.dayOfMonth = dayOfMonth;
	}

	@Override
	public Type getType() {
		return Type.DATE;
	}

	@Override
	public List<Temporal> getTemporal() {
		DayOfWeek dayOfWeek = null;
		int dayOfMonth = 0;

		dayOfWeek = TemporalBasicCaseParser.getDayOfWeek((this.dayOfWeek));
		dayOfMonth = Integer.parseInt(this.dayOfMonth);

		Date date = new Date();
		date.setDayOfWeek(dayOfWeek);
		date.setDay(dayOfMonth);
		Temporal temporal = TemporalObjectGenerator.generateTemporalDate(type,
				date);

		List<Temporal> temporalList = new ArrayList<Temporal>();
		temporalList.add(temporal);

		return temporalList;
	}
}
