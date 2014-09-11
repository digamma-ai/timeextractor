package com.codeminders.labs.timeextractor.rules.duration;

import java.util.List;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;

public class DurationRule1 extends BaseRule {
	private String duration;
	private String durationPeriod;

	public DurationRule1(String duration, String durationPeriod) {
		this.duration = duration;
		this.durationPeriod = durationPeriod;
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
