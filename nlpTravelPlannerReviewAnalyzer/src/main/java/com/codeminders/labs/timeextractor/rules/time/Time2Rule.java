package com.codeminders.labs.timeextractor.rules.time;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.Time;
import com.codeminders.labs.timeextractor.temporal.entites.Type;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;

public class Time2Rule extends BaseRule {

	private TemporalBasicCaseParser parser;
	protected Locale locale = Locale.US;
	protected double confidence = 0.8;
	private String hours;
	private String minutes;
	private String timezone;

	public Time2Rule(String hours, String minutes, String timezone) {
		parser = new TemporalBasicCaseParser();
		this.hours = hours;
		this.minutes = minutes;
		this.timezone = timezone;
	}

	@Override
	public Type getType() {
		return Type.TIME;
	}

	@Override
	public List<Temporal> getTemporal() {
		Time time = new Time();
		int hours = Integer.parseInt(this.hours);
		int minutes = Integer.parseInt(this.minutes);
		time.setHours(hours);
		time.setMinutes(minutes);
		int timezone = 0;
		if (this.timezone != null) {
			timezone = parser.getTimeZone(this.timezone);
			time.setTimezone(timezone);
		}
		Temporal temporal = TemporalObjectGenerator.generateTemporalTime(type,
				time);
		List<Temporal> temporalList = new ArrayList<Temporal>();
		temporalList.add(temporal);
		return temporalList;
	}
}
