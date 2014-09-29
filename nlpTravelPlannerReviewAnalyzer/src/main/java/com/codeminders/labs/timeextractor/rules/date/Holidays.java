package com.codeminders.labs.timeextractor.rules.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.constants.TemporalConstants;
import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Type;
import com.codeminders.labs.timeextractor.utils.TemporalParser;

public class Holidays extends Rule {
	protected double confidence = 0.99;
	private TemporalParser parser;
	private int priority = 1;
	private String rule = TemporalConstants.HOLIDAYS;

	public Holidays() {
		parser = new TemporalParser();
	}

	public Type getType() {
		return Type.DATE;
	}

	@Override
	public List<Temporal> getTemporal(String text) {
		List<Temporal> result = new ArrayList<Temporal>();
		Temporal holiday = parser.getHoliday(text);
		result.add(holiday);
		return result;
	}

	@Override
	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	@Override
	public double getConfidence() {
		return confidence;
	}

	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}

	@Override
	public int compareTo(Rule o) {
		return super.compare(this, o);
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

}
