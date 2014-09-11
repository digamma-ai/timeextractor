package com.codeminders.labs.timeextractor.rules.timeinterval;

import java.util.List;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;

public class TimeIntervalRule1 extends BaseRule {

	private String time;
	private String timeZone;

	public TimeIntervalRule1(String time, String timeZone) {
		super();
		this.time = time;
		this.timeZone = timeZone;
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
