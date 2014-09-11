package com.codeminders.labs.timeextractor.rules.date;

import java.util.List;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;

public class Holidays extends BaseRule {
	private String holidayName;

	public Holidays(String holidayName) {
		this.holidayName = holidayName;
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
