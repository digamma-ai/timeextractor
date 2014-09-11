package com.codeminders.labs.timeextractor.rules.set;

import java.util.List;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;

public class AllPeriod extends BaseRule {
	private String period;

	public AllPeriod(String period) {
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