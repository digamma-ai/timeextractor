package com.codeminders.labs.timeextractor.rules.time;

import java.util.List;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;

public class TimeOfDayRule extends BaseRule {
	private String timeOfDay;

	public TimeOfDayRule(String timeOfDay) {
		this.timeOfDay = timeOfDay;
	}

	@Override
	public Type getType() {
		return null;
	}

	@Override
	public List<Temporal> getTemporal() {
		return null;
	}

}
