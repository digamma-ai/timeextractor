package com.codeminders.labs.timeextractor.rules.set;

import java.util.List;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;

// every day, every week, every month
public class EveryPeriod extends BaseRule {
	private String period;

	public EveryPeriod(String period) {
		this.period = period;
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
