package com.codeminders.labs.timeextractor.rules.frequency;

import java.util.List;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;

public class FrequencyTime extends BaseRule {

	private String frequencyTime;

	public FrequencyTime(String frequencyTime) {
		this.frequencyTime = frequencyTime;
	}

	@Override
	public Type getType() {
		return Type.FREQUENCY;
	}

	@Override
	public List<Temporal> getTemporal() {
		// TODO Auto-generated method stub
		return null;
	}

}
